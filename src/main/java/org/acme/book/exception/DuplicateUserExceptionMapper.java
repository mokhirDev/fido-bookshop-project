package org.acme.book.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateUserExceptionMapper implements ExceptionMapper<DuplicateUserException> {
    @Override
    public Response toResponse(DuplicateUserException exception) {
        return Response.status(Response.Status.CONFLICT)
                .entity(exception.getMessage())
                .build();
    }
}
