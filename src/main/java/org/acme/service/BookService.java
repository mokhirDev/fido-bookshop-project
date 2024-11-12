package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.base.BaseRepository;
import org.acme.base.BaseService;
import org.acme.base.EntityMapper;
import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.entity.Book;
import org.acme.entity.User;
import org.acme.mapper.BookMapper;
import org.acme.mapper.UserMapper;
import org.acme.repository.BookRepository;

import java.util.Set;

@ApplicationScoped
public class BookService extends BaseService<BookRequestDTO, BookResponseDTO, Book, Long, UserResponseDTO> {
    @Inject
    BookRepository bookRepository;

    @Inject
    BookMapper bookMapper;

    @Inject
    UserMapper userMapper;


    @Override
    protected BaseRepository<Book, Long> getRepository() {
        return bookRepository;
    }

    @Override
    protected EntityMapper<BookRequestDTO, BookResponseDTO, Book> getMapper() {
        return bookMapper;
    }

//    @Override
//    protected Set<UserResponseDTO> getRelatedEntities(Book entity) {
//        Set<User> authors = entity.getAuthors();
//        return userMapper.toDTOSet(authors);
//    }
//
//    @Override
//    protected void setRelatedDTOs(BookResponseDTO dto, Set<UserResponseDTO> relatedDTOs) {
//        dto.setAuthors(relatedDTOs);
//    }

}
