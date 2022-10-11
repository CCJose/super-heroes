package com.superheroes.controller;


import com.superheroes.model.Superhero;
import com.superheroes.service.SuperheroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SuperheroController {

    private final SuperheroService superheroService;

    @GetMapping("/superheroes")
    public ResponseEntity<List<Superhero>> findAll(
            @RequestParam(name = "name.contains", defaultValue = "", required = false) String name
    ) {
        return ResponseEntity.ok(superheroService.findAll(name));
    }

    @GetMapping("/superheroes/{id}")
    public ResponseEntity<Superhero> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(superheroService.findOne(id));
    }

    @PutMapping("/superheroes/{id}")
    public ResponseEntity<Superhero> findOne(@PathVariable Long id, @RequestBody @Valid Superhero superhero) {
        return ResponseEntity.ok(superheroService.update(id, superhero));
    }

    @DeleteMapping("/superheroes/{id}")
    public ResponseEntity<Superhero> delete(@PathVariable Long id) {
        superheroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
