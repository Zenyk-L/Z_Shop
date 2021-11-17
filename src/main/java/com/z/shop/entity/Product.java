package com.z.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Product implements Serializable {

    private Integer id;

    private String name;

    private String image;

    private Category category = new Category();

    private Integer quantity;

    private String description;

    private String color;

    private String scale;

    private Double price;

    private Date addingDate;

    private boolean deleted;


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        return deleted == product.deleted && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(image, product.image) && Objects.equals(category, product.category) && Objects.equals(quantity, product.quantity) && Objects.equals(description, product.description) && Objects.equals(color, product.color) && Objects.equals(scale, product.scale) && Objects.equals(price, product.price) && Objects.equals(addingDate, product.addingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, category, quantity, description, color, scale, price, addingDate, deleted);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", category id=" + category.getId() +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", scale='" + scale + '\'' +
                ", price=" + price +
                ", addingDate=" + addingDate +
                ", deleted=" + deleted +
                '}'+System.lineSeparator();
    }
}
