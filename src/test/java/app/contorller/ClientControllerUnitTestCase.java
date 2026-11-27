package app.contorller;

import app.domain.model.Client;
import app.domain.service.Impl.ClientServiceImpl;
import app.infrastructure.mapper.web.Impl.ClientWebMapper;
import app.web.controller.ClientController;
import app.web.dto.request.ClientRequest;
import app.web.dto.response.ClientResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerUnitTestCase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @MockitoBean
    private ClientWebMapper clientWebMapper;

    @MockitoBean
    private ClientServiceImpl clientService;

    @Test
    public void TestAddShouldReturnSuccess() throws Exception {
        ClientRequest request = new ClientRequest(
                "Test",
                "Test",
                "test@example.com",
                "+77777777777"
        );

        when(clientService.add(
                clientWebMapper.toDomainFromDto(request)
                )
        ).thenReturn(
                UUID.fromString("00000000-0000-0000-0000-000000000000")
        );

        mockMvc.perform(post("/api/v1/clients/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(
                        "Location",
                        Matchers.endsWith("/api/v1/clients/get/00000000-0000-0000-0000-000000000000")
                        )
                );

        verify(clientService, atLeastOnce()).add(clientWebMapper.toDomainFromDto(request));
    }


    @Test
    public void TestAddEmptyNameShouldReturnBadRequest() throws Exception {
        ClientRequest request = new ClientRequest(
                "",
                "Test",
                "test@example.com",
                "+77777777777"
        );

        when(clientService.add(
                        clientWebMapper.toDomainFromDto(request)
                )
        ).thenReturn(
                UUID.fromString("00000000-0000-0000-0000-000000000000")
        );

        mockMvc.perform(post("/api/v1/clients/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(clientService, never()).add(clientWebMapper.toDomainFromDto(request));
    }

    @Test
    public void TestAddIncorrectEmailShouldReturnBadRequest() throws Exception {
        ClientRequest request = new ClientRequest(
                "Test",
                "Test",
                "test",
                "+77777777777"
        );

        when(clientService.add(
                        clientWebMapper.toDomainFromDto(request)
                )
        ).thenReturn(
                UUID.fromString("00000000-0000-0000-0000-000000000000")
        );

        mockMvc.perform(post("/api/v1/clients/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(clientService, never()).add(clientWebMapper.toDomainFromDto(request));
    }

    @Test
    public void TestAddIncorrectPhoneShouldReturnBadRequest() throws Exception {
        ClientRequest request = new ClientRequest(
                "Test",
                "Test",
                "test",
                "99999999999"
        );

        when(clientService.add(
                        clientWebMapper.toDomainFromDto(request)
                )
        ).thenReturn(
                UUID.fromString("00000000-0000-0000-0000-000000000000")
        );

        mockMvc.perform(post("/api/v1/clients/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(clientService, never()).add(clientWebMapper.toDomainFromDto(request));
    }


    @Test
    public void TestGetShouldReturnSuccess() throws Exception {
        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        LocalDateTime fixedTime = LocalDateTime.of(2026, 11, 28, 12, 0, 0);
        Client client = new Client(
                uuid,
                "Test",
                "Test",
                "test@example.com",
                "+77777777777",
                fixedTime,
                fixedTime,
                fixedTime
        );

        ClientResponse responseDto = new ClientResponse(
                uuid,
                "Test",
                "Test",
                "test@example.com",
                "+77777777777",
                fixedTime,
                fixedTime,
                fixedTime
        );

        when(clientService.get(uuid)).thenReturn(client);
        when(clientWebMapper.toDto(client)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/clients/get/{uuid}", uuid)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.surname").value("Test"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+77777777777"))
                .andExpect(jsonPath("$.lastConsult").value("2026-11-28T12:00:00"))
                .andExpect(jsonPath("$.createdAt").value("2026-11-28T12:00:00"))
                .andExpect(jsonPath("$.updatedAt").value("2026-11-28T12:00:00"));

        verify(clientService, atLeastOnce()).get(uuid);
    }


    @Test
    public void testEditShouldReturnSuccess() throws Exception {
        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        LocalDateTime fixedTime = LocalDateTime.of(2026, 11, 28, 12, 0, 0);

        Client client = new Client(
                uuid,
                "EditedTest",
                "EditedTest",
                "edited_test@example.com",
                "+78777777777",
                fixedTime,
                fixedTime,
                fixedTime
        );

        ClientRequest requestDto = new ClientRequest(
                "EditedTest",
                "EditedTest",
                "edited_test@example.com",
                "+78777777777"
        );

        ClientResponse responseDto = new ClientResponse(
                uuid,
                "EditedTest",
                "EditedTest",
                "edited_test@example.com",
                "+78777777777",
                fixedTime,
                fixedTime,
                fixedTime
        );

        when(clientWebMapper.toDomainFromDto(any(ClientRequest.class))).thenReturn(client);
        when(clientService.edit(uuid, client)).thenReturn(client);
        when(clientWebMapper.toDto(client)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/clients/edit/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.name").value("EditedTest"))
                .andExpect(jsonPath("$.surname").value("EditedTest"))
                .andExpect(jsonPath("$.email").value("edited_test@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+78777777777"))
                .andExpect(jsonPath("$.lastConsult").value("2026-11-28T12:00:00"))
                .andExpect(jsonPath("$.createdAt").value("2026-11-28T12:00:00"))
                .andExpect(jsonPath("$.updatedAt").value("2026-11-28T12:00:00"));

        verify(clientService, atLeastOnce()).edit(uuid, client);
    }


    @Test
    public void testDeleteShouldReturnSuccess() throws Exception {
        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        LocalDateTime fixedTime = LocalDateTime.of(2026, 11, 28, 12, 0, 0);

        Client client = new Client(
                uuid,
                "Test",
                "Test",
                "test@example.com",
                "+7777777777",
                fixedTime,
                fixedTime,
                fixedTime
        );

        ClientResponse responseDto = new ClientResponse(
                uuid,
                "Test",
                "Test",
                "test@example.com",
                "+7777777777",
                fixedTime,
                fixedTime,
                fixedTime
        );

        when(clientService.delete(uuid)).thenReturn(client);
        when(clientWebMapper.toDto(client)).thenReturn(responseDto);

        mockMvc.perform(delete("/api/v1/clients/delete/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.surname").value("Test"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+7777777777"))
                .andExpect(jsonPath("$.lastConsult").value("2026-11-28T12:00:00"))
                .andExpect(jsonPath("$.createdAt").value("2026-11-28T12:00:00"))
                .andExpect(jsonPath("$.updatedAt").value("2026-11-28T12:00:00"));

        verify(clientService, atLeastOnce()).delete(uuid);
    }
}
