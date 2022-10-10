package com.superheroes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "superheroes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "superheroes_sq")
    @SequenceGenerator(
            name = "superheroes_sq",
            sequenceName = "superheroes_sq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
    private String name;
}
