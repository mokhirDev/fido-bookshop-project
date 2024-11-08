package org.acme.unit.service;

import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.dto.user.UserRequestDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.service.UserService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {
    private final UserService userService = new UserService();

    @Test
    public void testAddUser() {
        UserRequestDTO userRequestDTO = UserRequestDTO
                .builder()
                .name("Davron")
                .fullName("Davronov")
                .username("davronDev")
                .password("123123")
                .phone("+998903571847")
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
        assertNotNull(result);
        assertEquals("Davron", result.getName());
        assertEquals("Davronov", result.fullName);
        assertEquals("davronDev", result.username);
        assertEquals("+998903571847", result.phone);
        assertEquals("123123", result.getPassword());
        BookResponseDTO bookResponseDTO = result.getBooks().stream().filter(b -> b.getName().equals("ABC")).findFirst().get();
        assertEquals("ABC", bookResponseDTO.getName());
        assertEquals("ABC title", bookResponseDTO.getTitle());
        assertEquals(BigDecimal.valueOf(44.66), bookResponseDTO.getPrice());
    }
}
