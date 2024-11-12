package org.acme.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.base.BaseController;
import org.acme.base.BaseService;
import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.entity.Book;
import org.acme.service.BookService;

@Path("/api/books")
@ApplicationScoped
public class BookController extends BaseController<BookRequestDTO, BookResponseDTO, Long, UserResponseDTO> {

    @Inject
    BookService bookService;

    @Override
    protected BaseService<BookRequestDTO, BookResponseDTO, Book, Long, UserResponseDTO> getService() {
        return bookService;
    }

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBookById(@PathParam("id") Long id) {
//        return Response.ok(bookService.findByIdWithRelatedEntities(id)).build();
        return Response.ok(bookService.findById(id)).build();
    }
}
