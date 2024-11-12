package org.acme.integration.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.user.UserRequestDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.entity.User;
import org.acme.repository.UserRepository;
import org.acme.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class UserServiceIntegrationTest {
    @Inject
    UserService userService;
    UserRepository userRepository;
    UserResponseDTO expectedResponse;
    User user;

    @BeforeEach
    public void setUp() {
        expectedResponse = UserResponseDTO
                .builder()
                .name("Davron")
                .fullName("Davronov")
                .username("davronDev")
                .password("123123")
                .phone("+998903571844")
                .build();
        user = User
                .builder()
                .name("Davron")
                .fullName("Davronov")
                .username("davronDev")
                .password("123123")
                .phone("+998903571844")
                .build();
    }

    @Test
    public void testAddUser() {
        UserRequestDTO userRequestDTO = UserRequestDTO
                .builder()
                .name("Davron")
                .fullName("Davronov")
                .username("davronDev")
                .password("123123")
                .phone("+998903571844")
                .books(
                        Set.of(
                                BookRequestDTO
                                        .builder()
                                        .name("ABC")
                                        .title("ABC title")
                                        .published(LocalDate.of(2021, 5, 31))
                                        .price(BigDecimal.valueOf(44.66))
                                        .build()
                        )
                )
                .build();

        UserResponseDTO result = userService.save(userRequestDTO);
        assertUserResponseDTO(expectedResponse, result);
    }

    @Test
    public void testFindById() {
        UserResponseDTO expected = UserResponseDTO
                .builder()
                .name("Davron")
                .fullName("Davronov")
                .username("davronDev")
                .password("123123")
                .phone("+998903571844")
                .build();
        UserResponseDTO result = userService.findById(1L).orElse(null);
        assertUserResponseDTO(expected, result);
    }

    private void assertUserResponseDTO(UserResponseDTO expected, UserResponseDTO actual) {
        assertNotNull(actual, "Результат не должен быть null");
        assertEquals(expected.getName(), actual.getName(), "Имя не совпадает");
        assertEquals(expected.getFullName(), actual.getFullName(), "Полное имя не совпадает");
        assertEquals(expected.getUsername(), actual.getUsername(), "Имя пользователя (username) не совпадает");
        assertEquals(expected.getPhone(), actual.getPhone(), "Телефон не совпадает");
        assertEquals(expected.getPassword(), actual.getPassword(), "Пароль не совпадает");
    }
}
