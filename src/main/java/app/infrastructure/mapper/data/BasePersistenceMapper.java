package app.infrastructure.mapper.data;

/**
 * Mapper Domain <-> DataLayer
 * @param <D> domain data layer model
 * @param <E> entity model
 */
public interface BasePersistenceMapper <D, E>{
    E toEntity(D domain);
    D toDomainFromEntity(E entity);
}
