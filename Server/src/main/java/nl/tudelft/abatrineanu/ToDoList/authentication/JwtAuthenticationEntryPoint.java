package nl.tudelft.abatrineanu.ToDoList.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Authentication entry point for JWT security.
 * <p>
 * The authentication entry point is called when an unauthenticated client tries to access a protected resource.
 * This JWT authentication entry point returns a response indicating the request was unauthorized.
 * </p>
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        // Return an unauthorized response code
        response.addHeader("WWW-Authenticate", "Bearer");
        response.sendError(HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
    }
}