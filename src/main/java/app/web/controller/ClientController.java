package app.web.controller;

import app.domain.service.Impl.ClientServiceImpl;
import app.infrastructure.mapper.web.Impl.ClientWebMapper;
import app.web.dto.request.ClientRequest;
import app.web.dto.response.BaseResponse;
import app.web.dto.response.ClientResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientServiceImpl clientService;
    private final ClientWebMapper clientWebMapper;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponse> add(@Valid @RequestBody ClientRequest request) {
        UUID createdId = clientService.add(clientWebMapper.toDomainFromDto(request));

        URI location = MvcUriComponentsBuilder
                .fromMethodName(ClientController.class, "get", createdId)
                .build()
                .toUri();

        return ResponseEntity
                .created(location)
                .body(
                        new BaseResponse(
                                HttpStatus.CREATED.value(),
                                "Пользователь успешно созан",
                                LocalDateTime.now()
                        )
                );
    }

    @GetMapping("/get/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse get(@PathVariable("uuid") UUID uuid) {
        return clientWebMapper.toDto(
                clientService.get(uuid)
        );
    }

    @PutMapping("/edit/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse edit(@PathVariable("uuid") UUID uuid, @Valid @RequestBody ClientRequest request) {
        return clientWebMapper.toDto(
                clientService.edit(
                        uuid,
                        clientWebMapper.toDomainFromDto(request)
                )
        );
    }

    @DeleteMapping("/delete/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse delete(@PathVariable("uuid") UUID uuid) {
        return clientWebMapper.toDto(
                clientService.delete(uuid)
        );
    }

}
