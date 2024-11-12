package org.acme.integration.repository;

import org.acme.entity.Book;
import org.acme.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookRepositoryTest {
    BookRepository bookRepository = Mockito.mock(BookRepository.class);

    @Test
    public void testFindByIdBook() {
        Book expectedBook = Book.builder()
                .name("Effective Java")
                .title("A Guide to Best Practices in Java Programming")
                .price(BigDecimal.valueOf(45.99))
                .build();
        Mockito.when(bookRepository.findById(1L)).thenReturn(expectedBook);
        Book result = bookRepository.findById(1L);
        assertNotNull(result);
        assertEquals(expectedBook.getName(), result.getName(), "Название книги не совпадает");
        assertEquals(expectedBook.getPrice(), result.getPrice(), "Цена книги не совпадает");
        assertEquals(expectedBook.getTitle(), result.getTitle(), "Заголовок книги не совпадает");
    }
}
