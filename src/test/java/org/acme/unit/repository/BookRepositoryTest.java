package org.acme.unit.repository;

import org.acme.dto.book.BookResponseDTO;
import org.acme.entity.Book;
import org.acme.repository.BookRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookRepositoryTest {
    private final BookRepository repository = new BookRepository();

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
        Book result = repository.findById(1L);
        assertNotNull(result);
        assertEquals(expectedBook.getName(), result.getName());
        assertEquals(expectedBook.getPrice(), result.getPrice());
        assertEquals(expectedBook.getTitle(), result.getTitle());

//        User user = result.getAuthors().stream().filter(a -> a.username.equals("mokhirDev")).findFirst().orElse(null);
//        assertNotNull(user);
//        UserResponseDTO actualAuthor = userMapper.toDto(user);
//
//        assertEquals("mokhirDev", actualAuthor.getUsername());
//        assertEquals("Mokhirbek", actualAuthor.getName());
//        assertEquals("Makhkamov", actualAuthor.getFullName());
//        assertEquals("+998903571847", actualAuthor.getPhone());
    }
}
