package com.z.shop.service;

import com.z.shop.entity.Product;
import com.z.shop.shared.AbstractCRUD;

import java.util.Map;

public interface ProductService extends AbstractCRUD<Product> {
    Map<Integer, Product> readAllMap();
}
