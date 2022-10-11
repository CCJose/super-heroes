package com.superheroes.service;

import com.superheroes.aop.LogExecutionTime;
import com.superheroes.handler.ErrorCode;
import com.superheroes.handler.exception.SuperheroesException;
import com.superheroes.model.Superhero;
import com.superheroes.repository.SuperheroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class SuperheroService {

    private final SuperheroRepository superheroRepository;

    @Transactional(readOnly = true)
    @LogExecutionTime
    public List<Superhero> findAll(String name) {
        return superheroRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    @LogExecutionTime
    public Superhero findOne(Long id) {
        return superheroRepository.findById(id)
                .orElseThrow(() -> new SuperheroesException(ErrorCode.SUPERHERO_NOT_FOUND));
    }

    public Superhero update(Long id, Superhero superhero) {
        Superhero result = superheroRepository.findById(id)
                .orElseThrow(() -> new SuperheroesException(ErrorCode.SUPERHERO_NOT_FOUND));
        result.setName(superhero.getName());

        return result;
    }

    public void delete(Long id) {
        superheroRepository.deleteById(id);
    }
}
