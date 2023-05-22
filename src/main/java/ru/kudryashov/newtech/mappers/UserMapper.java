package ru.kudryashov.newtech.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.kudryashov.newtech.dto.UserDTO;
import ru.kudryashov.newtech.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO map(User user);

    @InheritInverseConfiguration
    User map(UserDTO userDTO);
}
