package nl.tudelft.abatrineanu.ToDoList.services;

import lombok.RequiredArgsConstructor;
import nl.tudelft.abatrineanu.ToDoList.authentication.JwtTokenGenerator;
import nl.tudelft.abatrineanu.ToDoList.models.AuthenticationRequest;
import nl.tudelft.abatrineanu.ToDoList.models.AuthenticationResponse;
import nl.tudelft.abatrineanu.ToDoList.models.RegisterRequest;
import nl.tudelft.abatrineanu.ToDoList.repositories.UserRepository;
import nl.tudelft.abatrineanu.ToDoList.user.Activity;
import nl.tudelft.abatrineanu.ToDoList.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import nl.tudelft.abatrineanu.ToDoList.user.exceptions.UsernameAlreadyInUseException;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequest request) throws Exception {

        var name = request.getName();
        if(userRepository.existsByName(name)) {
            throw new UsernameAlreadyInUseException(name);
        }

        var user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .toDoList(new ArrayList<Activity>())
                .build();

        userRepository.save(user);

        return user;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));

        //username and password are correct
        var user = userRepository.findByName(request.getName()).orElseThrow();

        return new AuthenticationResponse(jwtTokenGenerator.generateToken(user));
    }
}
