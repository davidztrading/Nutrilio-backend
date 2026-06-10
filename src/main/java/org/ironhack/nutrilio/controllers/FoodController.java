package org.ironhack.nutrilio.controllers;

import org.ironhack.nutrilio.models.Food;
import org.ironhack.nutrilio.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired private FoodRepository repo;

    @GetMapping public List<Food> getAll() { return repo.findAll(); }
    @PostMapping public Food create(@RequestBody Food food) { return repo.save(food); }
    @PutMapping("/{id}") public ResponseEntity<Food> update(@PathVariable Long id, @RequestBody Food foodDetails) {
        return repo.findById(id).map(f -> {
            f.setName(foodDetails.getName());
            return ResponseEntity.ok(repo.save(f));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}