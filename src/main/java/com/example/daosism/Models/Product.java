package com.example.daosism.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 9, max = 9)
    @Pattern(regexp = "[A-Z][A-Z][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")
    @Column(unique = true)
    private String article;

    @NotBlank
    private String Owner;

    private Integer Quantity;

    private Integer Price;

    @OneToOne(mappedBy = "product")
    private Cell cell;

    public Product(String name, String article, String owner, Integer quantity, Integer price, Integer num) {
        this.setID(num);
        this.name = name;
        this.article = article;
        this.Owner = owner;
        this.Quantity = quantity;
        this.Price = price;
    }

    public Product(String name, String article, String owner, Integer quantity, Integer price) {
        this.name = name;
        this.article = article;
        this.Owner = owner;
        this.Quantity = quantity;
        this.Price = price;
    }

    public void setID(int id) {
        this.id = id;
    }
}
