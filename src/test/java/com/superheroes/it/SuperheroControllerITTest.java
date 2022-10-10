package com.superheroes.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superheroes.model.Superhero;
import com.superheroes.repository.SuperheroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource( value = "classpath:application.yml")
public class SuperheroControllerITTest {

    @Autowired private SuperheroRepository superheroRepository;

    private ObjectMapper mapper = new ObjectMapper();
    @Autowired private MockMvc mvc;

    @Test
    void whenFindAll() throws Exception {
        List<Superhero> result = superheroRepository.findAllByNameContainingIgnoreCase("superman");

        if (result.size() < 1) {
            Assertions.fail("No hay datos para ejecutar el test");
        }

        mvc.perform(
                get("/api/v1/superheroes")
                .param("name.contains", "superman"))
        .andExpect(jsonPath("$[*].name").value(hasItems(result.get(0).getName())))
        .andExpect(jsonPath("$[*].id").value(hasItems(result.get(0).getId().intValue())));


    }

    @Test
    void whenModifyOk() throws Exception {
        Optional<Superhero> result = superheroRepository.findById(1L);
        Superhero expectedResult = new Superhero(1L, "TEST");

        if (result.isEmpty()) {
            Assertions.fail("No hay datos para ejecutar el test");
        }

        mvc.perform(
                put("/api/v1/superheroes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(expectedResult)))
        .andExpect(jsonPath("$.name").value(is(expectedResult.getName())))
        .andExpect(jsonPath("$.id").value(is(expectedResult.getId().intValue())));


    }
}
