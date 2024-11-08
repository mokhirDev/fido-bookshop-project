package org.acme.util.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.dto.book.BookResponseDTO;
import org.acme.entity.Book;
import org.acme.repository.BookRepository;
import org.acme.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class BookServiceTest {
    @InjectMock
    BookRepository bookRepository;

    @Inject
    BookService bookService;

    @Test
    public void testFindByIdBook() {
        Book book = new Book();
        book.setName("Effective Java");
        book.setTitle("A Guide to Best Practices in Java Programming");
        book.setPrice(BigDecimal.valueOf(45.99));

        Mockito.when(bookRepository.findByIdOptional(1L)).thenReturn(Optional.of(book));
        BookResponseDTO result = bookService.findById(1L).orElse(null);
        assertNotNull(result);
        assertEquals("Effective Java", result.getName());
        assertEquals("A Guide to Best Practices", result.getTitle());
        assertEquals(BigDecimal.valueOf(45.99), result.getPrice());

    }
}
