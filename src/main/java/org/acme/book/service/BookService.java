package org.acme.book.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.acme.book.aggregate.dto.BookRequestDTO;
import org.acme.book.aggregate.dto.BookResponseDTO;
import org.acme.book.aggregate.entity.Book;
import org.acme.book.aggregate.mapper.BookMapper;
import org.acme.book.repository.BookRepository;
import org.acme.user.aggregate.dto.UserResponseDTO;
import org.acme.user.service.UserService;

import java.util.Optional;

import static io.quarkus.hibernate.orm.panache.PanacheEntity_.id;

@ApplicationScoped
public class BookService {
    @Inject
    BookRepository bookRepository;
    @Inject
    UserService userService;
    @Inject
    BookMapper bookMapper;


    @Transactional
    public Optional<BookResponseDTO> findByName(String bookName) {
        Book book = bookRepository.find("name", bookName).firstResult();
        if (book == null) {
            return Optional.empty();
        }
        BookResponseDTO responseDTO = bookMapper.bookToBookResponseDTO(book);
        return Optional.of(responseDTO);
    }

    @Transactional
    public String deleteUserBook(Long userId, Long bookId) {
        UserResponseDTO userById = userService.findUserById(userId);
        if (userById.getBooks().contains(bookId)) {
            boolean deleteById = bookRepository.deleteById(bookId);
            if (deleteById) {
                return "Book deleted successfully from user list, with id: " + id;
            }
        }
        return "Book could not be deleted, with id: " + id;
    }

    @Transactional
    public BookResponseDTO getById(Long id) {
        Book byId = bookRepository.findById(id);
        return bookMapper.bookToBookResponseDTO(byId);
    }

    @Transactional
    public String deleteBook(Long bookId) {
        boolean deleteById = bookRepository.deleteById(bookId);
        if (deleteById) {
            return "Book deleted successfully, with id: " + id;
        }
        return "Book could not be deleted, with id: " + id;
    }

    @Transactional
    public BookResponseDTO addBook(BookRequestDTO book) {
        Book entity = bookMapper.bookToEntity(book);
        Book firstResult = bookRepository.find("name", book.getName()).firstResult();
        if (firstResult == null) {
            bookRepository.persist(entity);
            return bookMapper.bookToBookResponseDTO(entity);
        }
        throw new WebApplicationException("Book already exists: " + book.getName(), Response.Status.CONFLICT);
    }


}
