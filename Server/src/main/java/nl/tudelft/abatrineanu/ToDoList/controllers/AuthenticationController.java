package nl.tudelft.abatrineanu.ToDoList.controllers;

import lombok.RequiredArgsConstructor;
import nl.tudelft.abatrineanu.ToDoList.models.AuthenticationRequest;
import nl.tudelft.abatrineanu.ToDoList.models.AuthenticationResponse;
import nl.tudelft.abatrineanu.ToDoList.models.RegisterRequest;
import nl.tudelft.abatrineanu.ToDoList.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) throws Exception {
        authService.register(request);
        return ResponseEntity.ok().body("Registration successful");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws ResponseStatusException {
        try {
            return ResponseEntity.ok(authService.authenticate(request));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", e);
        }
    }
}
