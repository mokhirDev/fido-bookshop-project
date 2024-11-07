package org.acme.mapper;

import org.acme.base.EntityMapper;
import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface BookMapper extends EntityMapper<BookRequestDTO , BookResponseDTO, Book> {
}
