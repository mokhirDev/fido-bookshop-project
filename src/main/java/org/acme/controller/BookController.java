package org.acme.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.book.BookRequestDTO;
import org.acme.dto.book.BookResponseDTO;
import org.acme.service.BookService;
import java.util.List;

@Path("/api/books")
@ApplicationScoped
@Authenticated
public class BookController{

    @Inject
    BookService bookService;

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "author", "user"})
    public Response findBookById(@PathParam("id") Long id) {
//        return Response.ok(bookService.findByIdWithRelatedEntities(id)).build();
        return Response.ok(bookService.findById(id)).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("author")
    public Response create(BookRequestDTO dto) {
        return Response.ok(bookService.save(dto)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("author")
    public Response delete(@PathParam("id") Long id) {
        bookService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/all")
    @RolesAllowed({"admin", "author"})
    public Response findAll() {
        List<BookResponseDTO> all = bookService.findAll();
        return Response.ok(all).build();
    }
}
