package org.acme.mapper;

import org.acme.base.EntityMapper;
import org.acme.dto.user.UserRequestDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface UserMapper extends EntityMapper<UserRequestDTO, UserResponseDTO, User> {
}
