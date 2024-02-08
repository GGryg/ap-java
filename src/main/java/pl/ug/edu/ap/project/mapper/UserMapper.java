package pl.ug.edu.ap.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.ug.edu.ap.project.domain.User;
import pl.ug.edu.ap.project.domain.response.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> users);
}
