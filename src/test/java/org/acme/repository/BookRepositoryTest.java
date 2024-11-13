package org.acme.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entity.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class BookRepositoryTest {

    @Inject
    BookRepository bookRepository;

    @Test
    @Transactional
    public void testBookRepositoryFindById() {
        LocalDate published = LocalDate.of(2018, 1, 1);
        Book expected = Book.builder()
                .name("Effective Java")
                .price(BigDecimal.valueOf(45.99))
                .published(published)
                .title("A Guide to Best Practices in Java Programming")
                .build();

        Book result = bookRepository.findById(1L);
        assertBookEntityEquals(expected, result);
    }


    @Test
    @Transactional
    public void testBookRepositorySave() {
        Book newBook = Book
                .builder()
                .name("Test Book Name")
                .title("Test Book Title")
                .published(LocalDate.now())
                .price(BigDecimal.valueOf(99.99))
                .build();
        bookRepository.persist(newBook);
        assertNotNull(newBook.getId(), "ID книги должна быть назначена после сохранения");
        Book savedBook = bookRepository.findById(newBook.getId());
        assertNotNull(savedBook, "Сохранённая книга должна быть найдена");
        assertBookEntityEquals(newBook, savedBook);
    }

    @Test
    @Transactional
    public void testBookRepositoryDeleteById() {
        Book book = bookRepository.findAll().stream().findFirst().orElse(null);
        assertNotNull(book, "Результат не должен быть null");

        book.getAuthors().forEach(user -> user.getBooks().remove(book));
        book.getAuthors().clear();

        Long id = book.getId();
        bookRepository.deleteById(id);

        Book response = bookRepository.findByIdOptional(id).orElse(null);
        assertNull(response, "Результат должен быть равен 'null'");
    }

    private void assertBookEntityEquals(Book expected, Book actual) {
        assertNotNull(actual, "Результат не должен быть null");
        assertEquals(expected.getName(), actual.getName(), "Название книги не совпадает");
        assertEquals(expected.getPrice(), actual.getPrice(), "Цена книги не совпадает");
        assertEquals(expected.getTitle(), actual.getTitle(), "Заголовок книги не совпадает");
        assertEquals(expected.getPublished(), actual.getPublished(), "Дата публикации не совпадает");
    }
}
