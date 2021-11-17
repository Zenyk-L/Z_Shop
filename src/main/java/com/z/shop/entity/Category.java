package com.z.shop.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category implements Serializable {

    private Integer id;

//    private Integer categoryDescriptionId;

    Map<String, String> translations = new HashMap<>();

    private boolean deleted;

    public Category() {
    }

//    public Category(Integer categoryId) {
//        this.categoryDescriptionId = categoryId;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getCategoryDescriptionId() {
//        return categoryDescriptionId;
//    }
//
//    public void setCategoryDescriptionId(Integer categoryDescriptionId) {
//        this.categoryDescriptionId = categoryDescriptionId;
//    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(Map<String, String> translations) {
        this.translations = translations;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return deleted == category.deleted && Objects.equals(id, category.id) && Objects.equals(translations, category.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, translations, deleted);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", translations=" + translations +
                ", deleted=" + deleted +
                '}';
    }
}
