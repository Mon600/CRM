package app.infrastructure.mapper.web.Impl;


import app.domain.model.Client;
import app.infrastructure.mapper.web.BaseWebMapper;
import app.web.dto.request.ClientRequest;
import app.web.dto.response.ClientResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientWebMapper implements BaseWebMapper<Client, ClientRequest, ClientResponse> {
    @Override
    public Client toDomainFromDto(ClientRequest requestDto) {
        if (requestDto == null) {
            return null;
        }
        return new Client(
                requestDto.name(),
                requestDto.surname(),
                requestDto.email(),
                requestDto.phoneNumber()
        );
    }

    @Override
    public ClientResponse toDto(Client domain) {
        if (domain == null) {
            return null;
        }
        return new  ClientResponse(
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
