package com.superheroes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.superheroes.model.Superhero;
import com.superheroes.repository.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class SuperheroServiceTest {

    @Mock private SuperheroRepository superheroRepository;
    @InjectMocks private SuperheroService underTest;

    @Test
    void testFindAll() {
        //Given
        List<Superhero> expectedResult = List.of(new Superhero(1L, "TEST"));
        when(superheroRepository.findAllByNameContainingIgnoreCase(any())).thenReturn(expectedResult);

        //When
        List<Superhero> result = underTest.findAll(null);

        //Then
        assertEquals(expectedResult, result);
    }

    @Test
    void testUpdate() {
        //Given
        Superhero superhero = new Superhero(1L, "TEST");
        when(superheroRepository.findById(any())).thenReturn(Optional.of(new Superhero(1L, "Batman")));

        //When
        Superhero result = underTest.update(1L, superhero);

        //Then
        assertEquals(result, superhero);
    }

    @Test
    void testDelete() {
        //When
        underTest.delete(1L);

        //Then
        assertNotNull(null);
    }

}