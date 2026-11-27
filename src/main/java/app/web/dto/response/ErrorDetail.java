package app.web.dto.response;

import java.time.LocalDateTime;


public record ErrorDetail(int status, String detail, LocalDateTime timestamp) {}
