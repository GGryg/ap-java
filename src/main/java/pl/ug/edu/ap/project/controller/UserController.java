package pl.ug.edu.ap.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.ug.edu.ap.project.domain.User;
import pl.ug.edu.ap.project.domain.request.user.CreateUserRequest;
import pl.ug.edu.ap.project.domain.response.UserDTO;
import pl.ug.edu.ap.project.mapper.UserMapper;
import pl.ug.edu.ap.project.service.user.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok().body(UserMapper.INSTANCE.usersToUserDTOs(userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(UserMapper.INSTANCE.userToUserDTO(userService.getUser(id)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequest request) {
        User createdUser = userService.addUser(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(UserMapper.INSTANCE.userToUserDTO(createdUser));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> editUser(@PathVariable("id") Long id, @Valid @RequestBody CreateUserRequest request) {
        User editedUser = userService.editUser(id, request);

        return ResponseEntity.ok().body(UserMapper.INSTANCE.userToUserDTO(editedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
