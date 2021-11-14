package com.z.shop;

import com.z.shop.dao.CategoryDAO;
import com.z.shop.dao.impl.BucketDaoImpl;
import com.z.shop.dao.impl.CategoryDAOImpl;
import com.z.shop.entity.Category;
import com.z.shop.entity.Product;
import com.z.shop.service.CategoryService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.CategoryServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Date;
import java.sql.SQLException;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    BucketDaoImpl bucketDao = new BucketDaoImpl();

    public static void main(String[] args) throws SQLException {
        CategoryService categoryService = CategoryServiceImpl.getCategoryServiceImpl();
        Category category = new Category();
        category.setName("TEST8");
        categoryService.create(category);
        System.out.println(category);
        System.out.println(categoryService.read(6));
    }
}
