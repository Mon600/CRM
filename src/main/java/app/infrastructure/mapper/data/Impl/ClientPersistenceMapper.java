package app.infrastructure.mapper.data.Impl;

import app.datalayer.entity.ClientEntity;
import app.domain.model.Client;
import app.infrastructure.mapper.data.BasePersistenceMapper;
import org.springframework.stereotype.Component;


@Component
public class ClientPersistenceMapper implements BasePersistenceMapper<Client, ClientEntity> {
    @Override
    public Client toDomainFromEntity(ClientEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Client(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getLastConsult(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public ClientEntity toEntity(Client domain) {
        if (domain == null) {
            return null;
        }
        return new ClientEntity(
                domain.getId(),
                domain.getName(),
                domain.getSurname(),
                domain.getEmail(),
                domain.getPhoneNumber(),
                domain.getLastConsult(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
