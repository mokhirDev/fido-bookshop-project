package org.acme.controller;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.user.UserRequestDTO;
import org.acme.dto.user.UserResponseDTO;
import org.acme.service.UserService;

import java.util.List;
import java.util.Optional;

@Path("/api/users")
@ApplicationScoped
@Authenticated
public class UserController {

    @Inject
    UserService userService;
    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/info")
    public Response getUserInfo() {
        String username = securityIdentity.getPrincipal().getName();
        boolean isReader = securityIdentity.hasRole("reader");
        return Response.ok("Username: " + username + ", isReader: " + isReader).build();
    }

//    @GET
//    @Path("/get/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed("user")
//    public Response findUserById(@PathParam("id") Long id) {
//        return Response.ok(userService.findByIdWithRelatedEntities(id)).build();
//        return Response.ok(userService.findById(id)).build();
//    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin", "user"})
    public Response findById(@PathParam("id") Long id) {
        Optional<UserResponseDTO> entity = userService.findById(id);
        return entity.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response addUser(UserRequestDTO dto) {
        return Response.ok(userService.save(dto)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") Long id) {
        userService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/all")
    @RolesAllowed("admin")
    public Response findAll() {
        List<UserResponseDTO> all = userService.findAll();
        return Response.ok(all).build();
    }
}
