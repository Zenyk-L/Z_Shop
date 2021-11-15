package com.z.shop.service;

import com.z.shop.entity.Category;
import com.z.shop.shared.AbstractCRUD;

import java.util.List;

public interface CategoryService extends AbstractCRUD<Category> {

    Category createByName(String categoryName);
}
