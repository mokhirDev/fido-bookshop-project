package org.acme.base;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<Req, Res, ID, RelatedDTO> {

    protected abstract BaseService<Req, Res, ?, ID, RelatedDTO> getService();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","admin"})
    public Response findById(@PathParam("id") ID id) {
        Optional<Res> entity = getService().findById(id);
        return entity.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response create(Req dto) {
        return Response.ok(getService().save(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") ID id) {
        getService().delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/all")
    @RolesAllowed("admin")
    public Response findAll() {
        List<Res> all = getService().findAll();
        return Response.ok(all).build();
    }
}
