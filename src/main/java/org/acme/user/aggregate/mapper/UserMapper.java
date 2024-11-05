package org.acme.user.aggregate.mapper;

import org.acme.user.aggregate.dto.UserRequestDTO;
import org.acme.user.aggregate.dto.UserResponseDTO;
import org.acme.user.aggregate.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface UserMapper {
    UserResponseDTO toResponseDTO(User user);

    User toUser(UserRequestDTO userRequestDTO);

}
