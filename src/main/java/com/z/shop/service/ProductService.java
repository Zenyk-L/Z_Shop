package com.z.shop.service;

import com.z.shop.entity.Product;
import com.z.shop.shared.AbstractCRUD;

import java.util.List;
import java.util.Map;

public interface ProductService extends AbstractCRUD<Product> {
    Map<Integer, Product> readAllMap();
    Map<Integer, Product> readAllMapWithDeleted();
    List<Product> findByName(String searchingName) ;
    List<Product> readAllWithDeleted();
}
