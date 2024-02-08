package pl.ug.edu.ap.project.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "user already exists")
public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String username) {
        super("User with name: " + username + " already exists");
    }
}
