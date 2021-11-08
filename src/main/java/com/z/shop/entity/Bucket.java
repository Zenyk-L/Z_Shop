package com.z.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Bucket implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Date purchaseDate;
    private boolean deleted;

    public Bucket(){}

    public Bucket(Integer id, Integer userId, Integer productId, Date purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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
        Bucket bucket = (Bucket) o;
        return Objects.equals(id, bucket.id) && Objects.equals(userId, bucket.userId) && Objects.equals(productId, bucket.productId) && Objects.equals(purchaseDate, bucket.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, purchaseDate);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", purchaseDate=" + purchaseDate +
                ", deleted=" + deleted +
                '}'+System.lineSeparator();
    }
}
