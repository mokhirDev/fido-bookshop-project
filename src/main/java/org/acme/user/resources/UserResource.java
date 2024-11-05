package org.acme.user.resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.book.service.BookService;
import org.acme.user.aggregate.dto.UserRequestDTO;
import org.acme.user.aggregate.dto.UserResponseDTO;
import org.acme.user.service.UserService;

@Path("/api/user")
public class UserResource {
    @Inject
    UserService userService;
    @Inject
    BookService bookService;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@Valid UserRequestDTO user) {
        UserResponseDTO userResponseDTO = userService.addUser(user);
        return Response.ok(userResponseDTO).build();
    }

    @GET
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@QueryParam("byId") Long byId) {
        UserResponseDTO userResponseDTO = userService.findUserById(byId);
        return Response.ok(userResponseDTO).build();
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserBooks(@QueryParam("byId") Long userId) {
        return Response.ok(userService.getUserBooks(userId)).build();
    }

    @DELETE
    @Path("/book/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserBook(@QueryParam("userId") Long userId, @QueryParam("bookId") Long bookId) {
        return Response.ok(bookService.deleteUserBook(userId, bookId)).build();
    }
}
