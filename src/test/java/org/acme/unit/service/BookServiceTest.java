package org.acme.unit.service;

import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookServiceTest {

    BookService bookService = Mockito.mock(BookService.class);

    @Test
    public void testAddBook() {
        BookRequestDTO bookRequestDTO = BookRequestDTO.builder()
                .name("Test book")
                .price(BigDecimal.valueOf(12000.0))
                .published(LocalDate.now())
                .title("Test title")
                .build();
        BookResponseDTO bookResponseDTO = BookResponseDTO.builder()
                .name("Test book")
                .price(BigDecimal.valueOf(12000.0))
                .title("Test title")
                .build();
        Mockito.when(bookService.save(bookRequestDTO)).thenReturn(bookResponseDTO);
        BookResponseDTO result = bookService.save(bookRequestDTO);
        assertNotNull(result, "Результат не должен быть null");
        assertEquals(bookResponseDTO.getName(), result.getName(), "Название книги не совпадает");
        assertEquals(bookResponseDTO.getPrice(), result.getPrice(), "Цена книги не совпадает");
        assertEquals(bookResponseDTO.getTitle(), result.getTitle(), "Заголовок книги не совпадает");
    }

    @Test
    public void testFindByIdBook() {
        BookResponseDTO expectedBook = BookResponseDTO.builder()
                .name("Effective Java")
                .title("A Guide to Best Practices in Java Programming")
                .price(BigDecimal.valueOf(45.99))
                .build();

        Mockito.when(bookService.findById(1L)).thenReturn(Optional.of(expectedBook));
        BookResponseDTO result = bookService.findById(1L).orElse(null);
        assertNotNull(result, "Результат не должен быть null");
        assertEquals(expectedBook.getName(), result.getName(), "Название книги не совпадает");
        assertEquals(expectedBook.getPrice(), result.getPrice(), "Цена книги не совпадает");
        assertEquals(expectedBook.getTitle(), result.getTitle(), "Заголовок книги не совпадает");
    }

}
