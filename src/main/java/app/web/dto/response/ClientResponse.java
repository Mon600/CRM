package app.web.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientResponse(
        UUID uuid,
        String name,
        String surname,
        String email,
        String phoneNumber,
        LocalDateTime lastConsult,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {}
