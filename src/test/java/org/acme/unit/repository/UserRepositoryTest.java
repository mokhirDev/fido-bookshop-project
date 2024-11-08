package org.acme.unit.repository;

import org.acme.dto.user.UserResponseDTO;
import org.acme.entity.User;
import org.acme.mapper.UserMapper;
import org.acme.mapper.UserMapperImpl;
import org.acme.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();
    private final UserMapper userMapper = new UserMapperImpl();

    @Test
    public void findUserByIdTest() {
        Optional<User> resultOptional = userRepository.findByIdOptional(1L);
        User user = resultOptional.orElseThrow(() -> new RuntimeException("User Not Found"));
        UserResponseDTO userResponseDTO = userMapper.toDto(user);
        assertNotNull(userResponseDTO);
        assertEquals(1L, userResponseDTO.getId());
        assertEquals("Mokhirbek", userResponseDTO.getName());
        assertEquals("Makhkamov", userResponseDTO.getFullName());
        assertEquals("mokhirDev", userResponseDTO.getUsername());
//
//        BookResponseDTO bookResponseDTO = userResponseDTO.getBooks()
//                .stream()
//                .filter(b -> b.getId().equals(1L)).findFirst().get();
//        assertNotNull(bookResponseDTO);
//        assertEquals(1L, bookResponseDTO.getId());
//        assertEquals("Effective Java", bookResponseDTO.getName());
//        assertEquals("A Guide to Best Practices in Java Programming", bookResponseDTO.getTitle());

    }
}
