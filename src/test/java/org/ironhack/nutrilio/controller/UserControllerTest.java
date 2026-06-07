package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.dtos.UserResponseDTO;
import org.ironhack.nutrilio.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Test
    public void shouldReturnAllUsers() throws Exception {
        // Inicializa MockMvc manualmente aislando el controlador sin necesidad de cargar contextos pesados
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setEmail("test@nutrilio.com");

        when(userService.getAllUsers()).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test@nutrilio.com"));
    }
}