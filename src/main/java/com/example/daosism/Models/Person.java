package com.example.daosism.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String secondName;

    private Integer age;

    @NotBlank
    private String family;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Person(String name, String secondName, Integer age, String family, Location loc, Integer num) {
        this.setID(num);
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.family = family;
        this.location = loc;
    }

    public Person(String name, String secondname, Integer age, String family, Location loc) {
        this.name = name;
        this.secondName = secondname;
        this.age = age;
        this.family = family;
        this.location = loc;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
