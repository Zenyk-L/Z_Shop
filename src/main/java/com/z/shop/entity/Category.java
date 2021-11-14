package com.z.shop.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category implements Serializable {

    private Integer id;

    private String name;

    Map<String, String> translations = new HashMap<>();

    private boolean deleted;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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
        return deleted == category.deleted && Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(translations, category.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, translations, deleted);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", translations=" + translations +
                ", deleted=" + deleted +
                '}';
    }
}
