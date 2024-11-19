package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
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
import org.acme.dto.book.BookResponseDTO;
import org.acme.dto.user.UserRequestDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.entity.User;
import org.acme.service.UserService;

@Path("/api/users")
@ApplicationScoped
public class UserController extends BaseController<UserRequestDTO, UserResponseDTO, Long, BookResponseDTO> {

    @Inject
    UserService userService;

    @Override
    protected BaseService<UserRequestDTO, UserResponseDTO, User, Long, BookResponseDTO> getService() {
        return userService;
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response findUserById(@PathParam("id") Long id) {
//        return Response.ok(userService.findByIdWithRelatedEntities(id)).build();
        return Response.ok(userService.findById(id)).build();
    }
}
