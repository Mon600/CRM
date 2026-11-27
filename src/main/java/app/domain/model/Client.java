package app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Client {
    private final UUID id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDateTime lastConsult;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Client(String name, String surname, String email, String phoneNumber) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastConsult = LocalDateTime.now();
        this.createdAt = null;
        this.updatedAt = null;

    }
}
