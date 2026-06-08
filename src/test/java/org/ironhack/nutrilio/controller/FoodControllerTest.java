package org.ironhack.nutrilio.controller;

import org.ironhack.nutrilio.models.Food;
import org.ironhack.nutrilio.service.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// SOLO cargamos el controlador FoodController
@WebMvcTest(FoodController.class)
public class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService; // Spring inyecta automáticamente el mock aquí

    @Test
    public void shouldReturnAllFoods() throws Exception {
        Food food = new Food();
        food.setId(1L);
        food.setName("Manzana");
        food.setCalories(52);

        when(foodService.getAllFoods()).thenReturn(List.of(food));

        mockMvc.perform(get("/api/foods")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Manzana"))
                .andExpect(jsonPath("$[0].calories").value(52));
    }
}