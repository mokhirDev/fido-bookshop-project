package org.acme.base;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface BaseRepository<E, ID> extends PanacheRepositoryBase<E, ID> {
}

