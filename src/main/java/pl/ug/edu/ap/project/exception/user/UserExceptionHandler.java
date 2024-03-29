package pl.ug.edu.ap.project.exception.user;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(UserAlreadyExist.class)
    protected ResponseEntity<Object> handleUserAlreadyExist(UserAlreadyExist ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
