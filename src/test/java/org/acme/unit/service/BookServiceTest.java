package org.acme.unit.service;

import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.repository.BookRepository;
import org.acme.service.BookService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookServiceTest {
    private final BookService bookService = new BookService();

    @Test
    public void testAddBook() {
        BookRequestDTO bookRequestDTO = BookRequestDTO.builder()
                .name("Test book")
                .price(BigDecimal.valueOf(12000.0))
                .published(LocalDate.now())
                .title("Test title")
//                .authors(
//                        Set.of(
//                                new User(
//                                        "Test user",
//                                        "Test fullName",
//                                        "Test username",
//                                        "Test password",
//                                        "Test email",
//                                        "+998909099999",
//                                        null
//                                )))
                .build();
        BookResponseDTO result = bookService.save(bookRequestDTO);
        assertNotNull(result);
        assertEquals("Test title", result.getTitle());
        assertEquals("Test book", result.getName());
        assertEquals(String.valueOf(BigDecimal.valueOf(12000.0)), result.getPrice().toString());
    }

    @Test
    public void testFindByIdBook() {
        BookResponseDTO expectedBook = BookResponseDTO.builder()
                .name("Effective Java")
                .title("A Guide to Best Practices in Java Programming")
                .price(BigDecimal.valueOf(45.99))
//                .authors(
//                        Set.of(
//                                UserResponseDTO.builder()
//                                        .id(1L)
//                                        .name("Mokhirbek")
//                                        .fullName("Makhkamov")
//                                        .username("mokhirDev")
//                                        .phone("+998903571847")
//                                        .build()
//                        )
//                )
                .build();


        BookResponseDTO result = bookService.findById(1L).orElse(null);

        assertNotNull(result);

        assertEquals(expectedBook.getName(), result.getName());
        assertEquals(expectedBook.getPrice(), result.getPrice());
        assertEquals(expectedBook.getTitle(), result.getTitle());

//        UserResponseDTO expectedAuthor = expectedBook.getAuthors().iterator().next();

//        UserResponseDTO actualAuthor = result.getAuthors().stream()
//                .filter(a -> a.getId().equals(expectedAuthor.getId()))
//                .findFirst()
//                .orElse(null);
//
//        assertNotNull(actualAuthor);
//        assertEquals(expectedAuthor.getUsername(), actualAuthor.getUsername());
//        assertEquals(expectedAuthor.getName(), actualAuthor.getName());
//        assertEquals(expectedAuthor.getFullName(), actualAuthor.getFullName());
//        assertEquals(expectedAuthor.getPhone(), actualAuthor.getPhone());
    }

}
