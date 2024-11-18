package org.acme.service;


import io.quarkus.test.TestTransaction;
import jakarta.transaction.Transactional;
import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.entity.Book;
import org.acme.mapper.BookMapper;
import org.acme.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@TestTransaction
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    BookMapper bookMapper;
    @InjectMocks
    BookService bookService;

    @Test
    public void testAddBook() {
        LocalDate published = LocalDate.now();
        BookRequestDTO bookRequestDTO = BookRequestDTO.builder()
                .name("Test book")
                .price(BigDecimal.valueOf(12000.0))
                .published(published)
                .title("Test title")
                .build();

        BookResponseDTO bookResponseDTO = BookResponseDTO.builder()
                .name("Test book")
                .price(BigDecimal.valueOf(12000.0))
                .published(published)
                .title("Test title")
                .build();


        Book book = new Book();
        book.setName(bookRequestDTO.getName());
        book.setPrice(bookRequestDTO.getPrice());
        book.setPublished(published);
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthors(null);

        Mockito.when(bookMapper.toEntity(bookRequestDTO)).thenReturn(book);
        Mockito.when(bookMapper.toDto(book)).thenReturn(bookResponseDTO);

        BookResponseDTO result = bookService.save(bookRequestDTO);
        Mockito.verify(bookRepository, Mockito.times(1)).persist(Mockito.any(Book.class));
        assertBookResponseEquals(bookResponseDTO, result);
    }


    @Test
    public void testFindByIdBook() {
        Book book = new Book();
        book.setName("Effective Java");
        book.setPrice(BigDecimal.valueOf(45.99));
        book.setTitle("A Guide to Best Practices in Java Programming");

        Mockito.when(bookRepository.findByIdOptional(1L)).thenReturn(Optional.of(book));

        BookResponseDTO dto = BookResponseDTO.builder()
                .name("Effective Java")
                .price(BigDecimal.valueOf(45.99))
                .title("A Guide to Best Practices in Java Programming")
                .build();

        Mockito.when(bookMapper.toDto(book)).thenReturn(dto);

        BookResponseDTO result = bookService.findById(1L).orElse(null);

        assertBookResponseEquals(dto, result);
    }

    private void assertBookResponseEquals(BookResponseDTO expected, BookResponseDTO actual) {
        assertNotNull(actual, "Результат не должен быть null");
        assertEquals(expected.getName(), actual.getName(), "Название книги не совпадает");
        assertEquals(expected.getPrice(), actual.getPrice(), "Цена книги не совпадает");
        assertEquals(expected.getTitle(), actual.getTitle(), "Заголовок книги не совпадает");
    }
}
