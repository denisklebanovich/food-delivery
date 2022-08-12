package com.epam.training.fooddelivery.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "FOOD")
public class Food{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal calorie;
    private BigDecimal price;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;


    @Override
    public String toString() {
        return name + " " + price + " EUR";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id) && Objects.equals(name, food.name)
                && Objects.equals(calorie, food.calorie) && Objects.equals(description, food.description)
                && Objects.equals(price, food.price) && category == food.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, calorie, description, price, category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCalorie() {
        return calorie;
    }

    public void setCalorie(BigDecimal calorie) {
        this.calorie = calorie;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
