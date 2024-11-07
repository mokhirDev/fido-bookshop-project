package org.acme.base;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.util.Optional;
import java.util.Set;

public abstract class BaseService<Req, Res, E, ID, RelatedDTO> {

    protected abstract BaseRepository<E, ID> getRepository();

    protected abstract EntityMapper<Req, Res, E> getMapper();

    protected abstract Set<RelatedDTO> getRelatedEntities(E entity);


    protected abstract void setRelatedDTOs(Res dto, Set<RelatedDTO> relatedDTOs);

    @Transactional
    public Res save(Req dto) {
        E entity = getMapper().toEntity(dto);
        getRepository().persist(entity);
        return getMapper().toDto(entity);
    }

    public Optional<Res> findById(ID id) {
        return getRepository().findByIdOptional(id)
                .map(getMapper()::toDto);
    }

    @Transactional
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    public Res findByIdWithRelatedEntities(ID id) {
        Optional<E> entityOptional = getRepository().findByIdOptional(id);
        if (entityOptional.isEmpty()) {
            throw new WebApplicationException("Entity with id: %d not found".formatted(id));
        }

        E entity = entityOptional.get();
        Res entityDTO = getMapper().toDto(entity);

        Set<RelatedDTO> relatedDTOs = getRelatedEntities(entity);
        setRelatedDTOs(entityDTO, relatedDTOs);
        return entityDTO;
    }
}
