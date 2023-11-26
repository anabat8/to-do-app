package nl.tudelft.abatrineanu.ToDoList.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle the case where a user with the same name already exists
     * @param exception the exception to be handled
     * @return a response entity explaining the problem
     */
    @ExceptionHandler({UsernameAlreadyInUseException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<String> handleExceptionSameUsername(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Username " + exception.getMessage() + " is already in use");
    }

    /**
     * Handle the case where a user can't be authenticated due to bad credentials
     * @return a response entity explaining the problem
     */
    @ExceptionHandler({ResponseStatusException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<String> handleExceptionCannotAuthenticate() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Cannot authenticate due to invalid credentials");
    }

    /**
     * Handle the case where a user with a certain username is not found
     * @return a response entity explaining the problem
     */
    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<String> handleExceptionUsernameNotFound() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("User not found");
    }

    /**
     * Handle the case where an activity with a certain id is not found or has already been checked (done)
     * @return a response entity explaining the problem
     */
    @ExceptionHandler({ActivityNotFoundException.class, ActivityAlreadyCheckedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<String> handleExceptionActivityNotFoundOrAlreadyChecked(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
