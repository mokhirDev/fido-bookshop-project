package org.acme.util.repository;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.entity.Book;
import org.acme.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class BookRepositoryTest {

    @InjectMock
    BookRepository bookRepository;

    @Test
    public void testFindById() {
        Book book = new Book();
        book.setName("Effective Java");
        book.setTitle("A Guide to Best Practices in Java Programming");
        book.setPrice(BigDecimal.valueOf(45.99));

        Mockito.when(bookRepository.findByIdOptional(1L)).thenReturn(Optional.of(book));

        Book bookEntity = bookRepository.findByIdOptional(1L).orElse(null);
        assertNotNull(bookEntity);
        assertEquals("Effective Java", bookEntity.getName());
        assertEquals(BigDecimal.valueOf(45.99), bookEntity.getPrice());
    }
}
