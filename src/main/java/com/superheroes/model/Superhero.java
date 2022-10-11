package com.superheroes.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @NotNull
    private Long id;
    @NotNull
    @Size(max = 255)
    private String name;
}
