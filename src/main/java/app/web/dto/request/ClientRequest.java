package app.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientRequest(
        @NotBlank(message = "Заполните поле \"Имя\"")
        String name,
        @NotBlank(message = "Заполните поле \"Фамилия\"")
        String surname,
        @Email(message = "Проверьте корректность электронной почты")
        String email,
        @NotBlank(message = "Заполните поле \"Телефон\"")
        @Pattern(
                regexp = "^(\\+7|8)\\d{10}$",
                message = "Номер должен быть в формате +7XXXXXXXXXX или 8XXXXXXXXXX"
        )
        String phoneNumber) {}
