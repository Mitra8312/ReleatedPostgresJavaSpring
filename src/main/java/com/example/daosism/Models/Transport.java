package com.example.daosism.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "[А-Я][0-9][0-9][0-9][А-Я][А-Я][0-9][0-9][0-9]")
    @Column(unique = true)
    private String number;

    @NotBlank
    private String description;

    @ManyToOne
    private Person driver;

    @ManyToMany(mappedBy = "transport")
    private List<Location> locations;


    public Transport(String name, String number, String description, Person driver, Integer num) {
        this.setID(num);
        this.name = name;
        this.number = number;
        this.description = description;
        this.driver = driver;
    }

    public Transport(String name, String number, String description, Person driver) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.driver = driver;
    }

    public void setID(int id) {
        this.id = id;
    }
}
