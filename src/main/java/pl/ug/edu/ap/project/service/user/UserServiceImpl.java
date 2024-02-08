package pl.ug.edu.ap.project.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ug.edu.ap.project.domain.User;
import pl.ug.edu.ap.project.domain.request.user.CreateUserRequest;
import pl.ug.edu.ap.project.exception.user.UserAlreadyExist;
import pl.ug.edu.ap.project.exception.user.UserNotFoundException;
import pl.ug.edu.ap.project.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User addUser(CreateUserRequest request) {
        Optional<User> u = userRepository.findByUsername(request.getUsername());
        if(u.isPresent()){
            throw new UserAlreadyExist(request.getUsername());
        }
        User newUser = User.builder()
                .username(request.getUsername())
                .reviews(new ArrayList<>())
                .build();

        return userRepository.save(newUser);
    }

    public User editUser(Long id, CreateUserRequest request) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (!request.getUsername().isEmpty()) {
            foundUser.setUsername(request.getUsername());
        }

        return userRepository.save(foundUser);
    }

    public void deleteUser(Long id) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(foundUser);
    }
}
