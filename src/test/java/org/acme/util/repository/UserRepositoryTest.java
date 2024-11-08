package org.acme.util.repository;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.entity.User;
import org.acme.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class UserRepositoryTest {

    @InjectMock
    UserRepository userRepository;

    @Test
    public void testFindById() {
        User user = new User();
        user.setName("Mokhirbek");
        user.setFullName("Makhkamov");
        user.setUsername("mokhirDev");

        Mockito.when(userRepository.findByIdOptional(1L)).thenReturn(Optional.of(user));

        User userResult = userRepository.findByIdOptional(1L).orElse(null);
        assertNotNull(userResult);
        assertEquals("Mokhirbek", userResult.getName());
        assertEquals("Makhkamov", userResult.getName());
        assertEquals("mokhirDev", userResult.getName());
        assertEquals("+998903571847", userResult.getPhone());
    }
}

