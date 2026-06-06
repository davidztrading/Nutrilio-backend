package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.models.Food;
import org.ironhack.nutrilio.service.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// BLOQUE DE CONFIGURACIÓN DEL TEST
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;

    // BLOQUE DE PRUEBAS: Validación del catálogo de alimentos
    @Test
    public void shouldReturnAllFoods() throws Exception {
        // Configuración de un alimento ficticio (Manzana)
        Food food = new Food();
        food.setId(1L);
        food.setName("Manzana");
        food.setCalories(52);

        when(foodService.getAllFoods()).thenReturn(List.of(food));

        // Simulación de la llamada HTTP
        mockMvc.perform(get("/api/foods")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Manzana"))
                .andExpect(jsonPath("$[0].calories").value(52));
    }
}