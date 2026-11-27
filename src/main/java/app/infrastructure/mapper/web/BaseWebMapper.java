package app.infrastructure.mapper.web;

/**
 * Mapper Web <-> Domain
 * @param <D> - domain layer model type
 * @param <I> - web layer model type(intput/request)
 * @param <O> - web layer model type(output/response)
 *
 */
public interface BaseWebMapper <D, I, O>{
    D toDomainFromDto(I requestDto);
    O toDto(D domain);
}
