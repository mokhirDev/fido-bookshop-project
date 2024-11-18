package org.acme.base;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import java.util.Optional;

public abstract class BaseService<Req, Res, E, ID, RelatedDTO> {

    protected abstract BaseRepository<E, ID> getRepository();

    protected abstract EntityMapper<Req, Res, E> getMapper();

//    protected abstract Set<RelatedDTO> getRelatedEntities(E entity);


//    protected abstract void setRelatedDTOs(Res dto, Set<RelatedDTO> relatedDTOs);

    @Transactional
    public Res save(Req dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO cannot be null");
        }
        E entity = getMapper().toEntity(dto);
        try {
            getRepository().persist(entity);
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to save entity", e);
        }
        return getMapper().toDto(entity);
    }

    @Transactional
    public Optional<Res> findById(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return getRepository().findByIdOptional(id)
                .map(getMapper()::toDto);
    }

    @Transactional
    public void delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        try {
            boolean deleted = getRepository().deleteById(id);
            if (!deleted) {
                throw new EntityNotFoundException("Entity with ID " + id + " not found");
            }
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to delete entity with ID " + id, e);
        }
    }


//    public Res findByIdWithRelatedEntities(ID id) {
//        Optional<E> entityOptional = getRepository().findByIdOptional(id);
//        if (entityOptional.isEmpty()) {
//            throw new WebApplicationException("Entity with id: %d not found".formatted(id));
//        }
//
//        E entity = entityOptional.get();
//        Res entityDTO = getMapper().toDto(entity);
//
//        Set<RelatedDTO> relatedDTOs = getRelatedEntities(entity);
//        setRelatedDTOs(entityDTO, relatedDTOs);
//        return entityDTO;
//    }
}
