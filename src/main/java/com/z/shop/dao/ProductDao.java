package com.z.shop.dao;

import com.z.shop.entity.Product;
import com.z.shop.shared.AbstractCRUD;

import java.util.List;

public interface ProductDao extends AbstractCRUD<Product> {
    List<Product> readAllWithDeleted();
}
