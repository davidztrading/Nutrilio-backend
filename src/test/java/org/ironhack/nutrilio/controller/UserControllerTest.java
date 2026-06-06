package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.models.User;
import org.ironhack.nutrilio.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Actualizado al nuevo estándar de Spring Boot 3
    @MockitoBean
    private UserService userService;

    @Test
    public void shouldReturnAllUsers() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@nutrilio.com");

        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test@nutrilio.com"));
    }
}