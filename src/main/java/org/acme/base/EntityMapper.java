package org.acme.base;

import java.util.Set;

public interface EntityMapper<Req, Res, E> {
    E toEntity(Req dto);
    Res toDto(E entity);
    Set<Res> toDTOSet(Set<E> entities);
}
