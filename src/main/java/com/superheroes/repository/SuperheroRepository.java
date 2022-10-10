package com.superheroes.repository;

import com.superheroes.model.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

    public List<Superhero> findAllByNameContainingIgnoreCase(String name);
}
