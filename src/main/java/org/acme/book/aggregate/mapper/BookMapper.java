package org.acme.book.aggregate.mapper;

import org.acme.book.aggregate.dto.BookRequestDTO;
import org.acme.book.aggregate.dto.BookResponseDTO;
import org.acme.book.aggregate.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface BookMapper {
    BookResponseDTO bookToBookResponseDTO(Book book);

    Book bookToEntity(BookRequestDTO bookRequestDTO);
}
