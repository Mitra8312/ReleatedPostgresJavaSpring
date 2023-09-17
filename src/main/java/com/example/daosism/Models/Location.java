package com.example.daosism.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    @Column(unique = true)
    private String address;

    @OneToMany(mappedBy = "loc")
    private List<Cell> cells = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "locations_transport",
        joinColumns = @JoinColumn(name = "location_id"),
        inverseJoinColumns = @JoinColumn(name = "transport_id"))
    private List<Transport> transport;

    @OneToMany(mappedBy = "location")
    private List<Person> People;


    public Location(String name, String description, String address, Integer num) {
        this.setID(num);
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public Location(String name, String address, String description) {
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int id) {
        this.id = id;
    }
}
