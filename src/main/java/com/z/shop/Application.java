package com.z.shop;

import com.z.shop.dao.CategoryDAO;
import com.z.shop.dao.impl.BucketDaoImpl;
import com.z.shop.dao.impl.CategoryDAOImpl;
import com.z.shop.entity.Category;
import com.z.shop.entity.Product;
import com.z.shop.service.CategoryService;
import com.z.shop.service.LanguageService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.CategoryServiceImpl;
import com.z.shop.service.impl.LanguageServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    BucketDaoImpl bucketDao = new BucketDaoImpl();

    public static void main(String[] args) throws SQLException {
//        CategoryService categoryService = CategoryServiceImpl.getCategoryServiceImpl();
//        Category category = new Category();
//        category.setName("TEST_000");
//        Map<String, String> categoryTranslations = category.getTranslations();
//        categoryTranslations.put("ua","Спроба_000");
//        categoryTranslations.put("pl","Proba_000");
//        categoryService.create(category);
////        System.out.println(category);
////        for (int i = 0; i < 18; i++) {
////            System.out.println(categoryService.read(i));
////
////        }
////        System.out.println(categoryService.readAll());
//        LanguageService languageService = LanguageServiceImpl.getLanguageServiceImpl();
//        System.out.println(languageService.readAll());

        CategoryService categoryService = CategoryServiceImpl.getCategoryServiceImpl();
        System.out.println(categoryService.create(new Category()));
//        System.out.println(productService.readAll());
//        System.out.println(productService.findByName("Мітсубісі"));

    }
}
