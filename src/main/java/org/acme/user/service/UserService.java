package org.acme.user.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.book.aggregate.dto.BookResponseDTO;
import org.acme.book.aggregate.mapper.BookMapper;
import org.acme.book.exception.DuplicateUserException;
import org.acme.user.aggregate.dto.UserRequestDTO;
import org.acme.user.aggregate.dto.UserResponseDTO;
import org.acme.user.aggregate.entity.User;
import org.acme.user.aggregate.mapper.UserMapper;
import org.acme.user.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    @Inject
    UserMapper userMapper;
    @Inject
    BookMapper bookMapper;

    @Transactional
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        User existingUserByUsername = userRepository.find("username", userRequestDTO.username).firstResult();
        if (existingUserByUsername != null) {
            throw new DuplicateUserException("Username already exists: " + userRequestDTO.getUsername());
        }

        User existingUserByEmail = userRepository.find("email", userRequestDTO.email).firstResult();
        if (existingUserByEmail != null) {
            throw new DuplicateUserException("Email already exists: " + userRequestDTO.getEmail());
        }

        User entity = userMapper.toUser(userRequestDTO);
        userRepository.persist(entity);
        return userMapper.toResponseDTO(entity);
    }


    public UserResponseDTO findUserById(Long id) {
        User byId = userRepository.findById(id);
        if (byId == null) {
            return null;
        }
        return userMapper.toResponseDTO(byId);
    }

    public List<BookResponseDTO> getUserBooks(Long userId) {
        User byId = userRepository.findById(userId);
        return byId.getBooks().stream()
                .map(bookMapper::bookToBookResponseDTO)
                .toList();
    }
}
