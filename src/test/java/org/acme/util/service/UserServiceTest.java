package org.acme.util.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.dto.user.UserResponseDTO;
import org.acme.entity.User;
import org.acme.repository.UserRepository;
import org.acme.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class UserServiceTest {
    @InjectMock
    UserRepository userRepository;

    @Inject
    UserService userService;

    @Test
    public void testFindByIdUser() {
        User user = User
                .builder()
                .name("Mokhirbek")
                .fullName("Makhkamov")
                .username("mokhirDev")
                .phone("+998903571847")
                .build();

        Mockito.when(userRepository.findByIdOptional(1L)).thenReturn(Optional.of(user));
        UserResponseDTO result = userService.findById(1L).orElse(null);
        assertNotNull(result);
        assertEquals("Mokhirbek", result.getName());
        assertEquals("Makhkamov", result.getFullName());
        assertEquals("mokhirDev", result.getUsername());
        assertEquals("+998903571847", result.getPhone());

    }
}
