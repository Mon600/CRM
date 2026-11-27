package app.domain.service.Impl;

import app.datalayer.entity.ClientEntity;
import app.datalayer.repository.ClientRepository;
import app.domain.exceptions.ClientNotFoundException;
import app.domain.model.Client;
import app.domain.service.CrudService;
import app.infrastructure.mapper.data.Impl.ClientPersistenceMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements CrudService<Client> {
    private final ClientRepository clientRepository;
    private final ClientPersistenceMapper clientMapper;

    @Override
    @Transactional(rollbackOn =  Exception.class)
    public UUID add(Client client) {
        ClientEntity clientEntity = clientMapper.toEntity(client);
        clientRepository.save(clientEntity);
        return clientEntity.getId();
    }

    @Override
    public Client get(UUID uuid) {
        ClientEntity clientEntity = clientRepository.findById(uuid)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return clientMapper.toDomainFromEntity(clientEntity);
    }

    @Override
    @Transactional(rollbackOn =  Exception.class)
    public Client edit(UUID uuid, Client client) {
        ClientEntity clientEntity = clientRepository.findById(uuid)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        clientEntity.setFirstName(client.getName());
        clientEntity.setLastName(client.getSurname());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setPhoneNumber(client.getPhoneNumber());

        clientRepository.save(clientEntity);
        return clientMapper.toDomainFromEntity(clientEntity);
    }

    @Override
    @Transactional(rollbackOn =  Exception.class)
    public Client delete(UUID uuid) throws ClientNotFoundException {
        Client existing = get(uuid);
        clientRepository.deleteById(uuid);
        return existing;
    }
}
