package com.z.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Product implements Serializable {

    private Integer id;

    private String name;

    private String image;

    private String category;

    private Integer quantity;

    private String description;

    private String color;

    private String scale;

    private Double price;

    private Date addingDate;

    private boolean deleted;

    public Product() {
    }

    public Product(Integer id, String name, String image, String category, String description, String color, String scale, Double price, Date addingDate) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.description = description;
        this.color = color;
        this.scale = scale;
        this.price = price;
        this.addingDate = addingDate;
    }

    public Product(Integer id, String name, String image, String category, Integer quantity, String description, String color, String scale, Double price, Date addingDate, boolean deleted) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.description = description;
        this.color = color;
        this.scale = scale;
        this.price = price;
        this.addingDate = addingDate;
        this.deleted = deleted;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(category, product.category) && Objects.equals(description, product.description) && Objects.equals(color, product.color) && Objects.equals(scale, product.scale) && Objects.equals(price, product.price) && Objects.equals(addingDate, product.addingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, description, color, scale, price, addingDate);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", scale='" + scale + '\'' +
                ", price=" + price +
                ", addingDate=" + addingDate +
                ", deleted=" + deleted +
                '}'+System.lineSeparator();
    }
}
