package org.acme.book.resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.book.aggregate.dto.BookRequestDTO;
import org.acme.book.aggregate.dto.BookResponseDTO;
import org.acme.book.service.BookService;

@Path("/api/book")
public class BookResource {
    @Inject
    BookService bookService;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(@Valid BookRequestDTO book) {
        BookResponseDTO responseDTO = bookService.addBook(book);
        return Response.ok(responseDTO).build();
    }


    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@QueryParam("bookId") Long bookId) {
        return Response.ok(bookService.deleteBook(bookId)).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@QueryParam("byId") Long id) {
        return Response.ok(bookService.getById(id)).build();
    }

}
