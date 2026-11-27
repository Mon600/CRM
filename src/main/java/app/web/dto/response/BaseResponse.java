package app.web.dto.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record BaseResponse(int code, String message, LocalDateTime timestamp) {}
