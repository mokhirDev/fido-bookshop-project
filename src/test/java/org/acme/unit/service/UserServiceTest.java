package org.acme.unit.service;

import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.user.UserRequestDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {
    UserService userService = Mockito.mock(UserService.class);

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
        UserResponseDTO expectedResponse = UserResponseDTO
                .builder()
                .name("Davron")
                .fullName("Davronov")
                .username("davronDev")
                .password("123123")
                .phone("+998903571844")
                .build();
        Mockito.when(userService.save(userRequestDTO)).thenReturn(expectedResponse);
        UserResponseDTO result = userService.save(userRequestDTO);
        assertNotNull(result, "Результат не должен быть null");
        assertEquals(expectedResponse.getName(), result.getName(), "Имя не совпадает");
        assertEquals(expectedResponse.getFullName(), result.getFullName(), "Полное имя не совпадает");
        assertEquals(expectedResponse.getUsername(), result.getUsername(), "Имя пользователя (username) не совпадает");
        assertEquals(expectedResponse.getPhone(), result.getPhone(), "Телефон не совпадает");
        assertEquals(expectedResponse.getPassword(), result.getPassword(), "Пароль не совпадает");

    }
}
