package com.z.shop.service.impl;

import com.z.shop.dao.CategoryDAO;
import com.z.shop.dao.impl.CategoryDAOImpl;
import com.z.shop.entity.Category;
import com.z.shop.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {
    private static CategoryService categoryServiceImpl;

    private CategoryDAO categoryDAO ;

    private CategoryServiceImpl(){
        categoryDAO = new CategoryDAOImpl();
    }

    public static CategoryService getCategoryServiceImpl() {
        if(categoryServiceImpl == null){
            categoryServiceImpl= new CategoryServiceImpl();
        }
        return categoryServiceImpl;
    }
//    @Override
//    public Category createByName(String categoryName) {
//
//        return categoryDAO.create(new Category());
//    }

    @Override
    public Category create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public Category read(Integer id) {
        return categoryDAO.read(id);
    }

    @Override
    public Category update(Category category) {
        return categoryDAO.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryDAO.delete(id);
    }

    @Override
    public List<Category> readAll() {
        return categoryDAO.readAll();
    }


//    @Override
//    public List<String> getAllCategoriesNames() {
//        List<Category> categories = readAll();
//        List<String> categoryNames = categories.stream().map(c -> c.getName()).collect(Collectors.toList());
//        return categoryNames;
//    }
}
