package app.domain.service;

import java.util.UUID;

public interface CrudService<T> {
    public UUID add(T obj);

    public T get(UUID uuid);

    public T edit(UUID uuid, T obj);

    public T delete(UUID uuid);
}
