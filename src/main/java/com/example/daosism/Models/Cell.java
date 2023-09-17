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
@Table(name = "cell")
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    private Integer level;

    @NotBlank
    @Column(unique = true)
    private String hash;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location loc;

    @OneToOne
    private Product product;

    public Cell(String name, int level, String hash, Location loc, Product product, int num) {
        this.setID(num);
        this.name = name;
        this.level = level;
        this.hash = hash;
        this.loc = loc;
        this.product = product;
    }

    public Cell(String name, Integer level, String hash, Location loc, Product product) {

        this.name = name;
        this.level = level;
        this.hash = hash;
        this.loc = loc;
        this.product = product;
    }

    public void setID(int id) {
        this.id = id;
    }
}
