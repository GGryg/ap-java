package pl.ug.edu.ap.project.service.user;

import pl.ug.edu.ap.project.domain.User;
import pl.ug.edu.ap.project.domain.request.user.CreateUserRequest;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long id);

    User addUser(CreateUserRequest request);

    User editUser(Long id, CreateUserRequest request);

    void deleteUser(Long id);

}
