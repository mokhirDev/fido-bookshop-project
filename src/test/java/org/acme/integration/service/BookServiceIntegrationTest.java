package org.acme.integration.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.service.BookService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
public class BookServiceIntegrationTest {

    @Inject
    BookService bookService;

//    @Test
//    public void testSaveAndRetrieveBook() {
//        LocalDate published = LocalDate.now();
//        BookRequestDTO bookRequestDTO = BookRequestDTO.builder()
//                .id(22L)
//                .name("Test book")
//                .price(BigDecimal.valueOf(12000.0))
//                .published(published)
//                .title("Test title")
//                .build();
//
//        BookResponseDTO savedBook = bookService.save(bookRequestDTO);
//        assertBookResponseEquals(bookRequestDTO, savedBook);
//
//        BookResponseDTO retrievedBook = bookService.findById(savedBook.getId()).orElse(null);
//        assertBookResponseEquals(bookRequestDTO, retrievedBook);
//    }
//
//    private void assertBookResponseEquals(BookRequestDTO expected, BookResponseDTO actual) {
//        assertNotNull(actual, "Результат не должен быть null");
//        assertEquals(expected.getName(), actual.getName(), "Название книги не совпадает");
//        assertEquals(expected.getPrice(), actual.getPrice(), "Цена книги не совпадает");
//        assertEquals(expected.getTitle(), actual.getTitle(), "Заголовок книги не совпадает");
//        assertEquals(expected.getPublished(), actual.getPublished(), "Дата публикации не совпадает");
//    }
}
