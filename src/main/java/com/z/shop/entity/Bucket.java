package com.z.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Bucket implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Date purchaseDate;
    private boolean deleted;
    private String status;


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return deleted == bucket.deleted && Objects.equals(id, bucket.id) && Objects.equals(userId, bucket.userId) && Objects.equals(productId, bucket.productId) && Objects.equals(quantity, bucket.quantity) && Objects.equals(purchaseDate, bucket.purchaseDate) && Objects.equals(status, bucket.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, quantity, purchaseDate, deleted, status);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", purchaseDate=" + purchaseDate +
                ", deleted=" + deleted +
                ", status='" + status + '\'' +
                '}'+ System.lineSeparator();
    }
}
