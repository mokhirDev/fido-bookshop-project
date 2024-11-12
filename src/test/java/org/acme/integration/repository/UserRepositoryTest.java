package org.acme.integration.repository;

import org.acme.entity.User;
import org.acme.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest {
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    public void findUserByIdTest() {
        User expectedUser = User
                .builder()
                .name("Mokhirbek")
                .fullName("Makhkamov")
                .username("mokhirDev")
                .phone("+998903571847")
                .build();
        Mockito.when(userRepository.findById(1L)).thenReturn(expectedUser);
        User result = userRepository.findById(1L);
        assertEquals(expectedUser.id, result.id, "ID пользователя не совпадает");
        assertEquals(expectedUser.name, result.name, "Имя пользователя не совпадает");
        assertEquals(expectedUser.fullName, result.fullName, "Полное имя пользователя не совпадает");
        assertEquals(expectedUser.username, result.username, "Имя пользователя (username) не совпадает");
        assertEquals(expectedUser.phone, result.phone, "Телефон пользователя не совпадает");
    }
}
