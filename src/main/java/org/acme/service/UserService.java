package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.base.BaseRepository;
import org.acme.base.BaseService;
import org.acme.base.EntityMapper;
import org.acme.dto.book.BookResponseDTO;
import org.acme.dto.user.UserRequestDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.entity.Book;
import org.acme.entity.User;
import org.acme.mapper.BookMapper;
import org.acme.mapper.UserMapper;
import org.acme.repository.UserRepository;
import java.util.Set;

@ApplicationScoped
public class UserService extends BaseService<UserRequestDTO, UserResponseDTO, User, Long, BookResponseDTO> {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Inject
    BookMapper bookMapper;

    @Override
    protected BaseRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected EntityMapper<UserRequestDTO, UserResponseDTO, User> getMapper() {
        return userMapper;
    }

//    @Override
//    protected Set<BookResponseDTO> getRelatedEntities(User entity) {
//        Set<Book> books = entity.getBooks();
//        return bookMapper.toDTOSet(books);
//    }

//    @Override
//    protected void setRelatedDTOs(UserResponseDTO dto, Set<BookResponseDTO> relatedDTOs) {
//        dto.setBooks(relatedDTOs);
//    }

}
