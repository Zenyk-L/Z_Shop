package com.z.shop.dao.impl;

import com.z.shop.dao.CategoryDAO;
import com.z.shop.entity.Category;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger LOGGER = LogManager.getLogger(CategoryDAOImpl.class);
    private static DBManager dbManager = DBManager.getInstance();

    private static String CREATE_CATEGORY = "INSERT INTO category () VALUE ()";

    private static String CREATE_CATEGORY_DESCR = "INSERT INTO category_description (category_id, language_id, category_name) VALUE (?,?,?)";
    private static String READ_ALL = "SELECT * FROM category WHERE deleted = false";
    private static String READ_BY_ID = "SELECT c.id, cd.language_id, cd.category_name FROM  category c  LEFT JOIN category_description cd on c.id = cd.category_id WHERE c.id=? AND c.deleted = false ";  //"/*SELECT * FROM category  WHERE id =? AND deleted = false; */SELECT c.id, c.name, cd.language_id, cd.category_name FROM category c LEFT JOIN category_description cd on c.id = cd.category_id WHERE c.id =? AND c.deleted = false ;";


    @Override
    public Category create(Category category) {

        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            category.setId(resultSet.getInt(1));


            PreparedStatement preparedStatement2 = connection.prepareStatement(CREATE_CATEGORY_DESCR);//, Statement.RETURN_GENERATED_KEYS)) {
            Iterator<String> iterator = category.getTranslations().keySet().iterator();
            while (iterator.hasNext()) {
                String lang_code = iterator.next();

                int k = 0;
                preparedStatement2.setInt(++k, category.getId());
                preparedStatement2.setString(++k, lang_code);
                preparedStatement2.setString(++k, category.getTranslations().get(lang_code));

                preparedStatement2.executeUpdate();

            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return category;
    }

    @Override
    public Category read(Integer id) {
        Category category =null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            category = new Category();
            Map<String, String> categoryTranslations = category.getTranslations();

            while (resultSet.next()) {
                if(category.getId() == null) {
                    category.setId(resultSet.getInt("id"));
                }

                String langCode = resultSet.getString("language_id");
                String categoryName = resultSet.getString("category_name");

                categoryTranslations.put(langCode, categoryName);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return category;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Category> readAll() {
        // getting from db list of all categories
        List<Category> categoryRecords = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Category category = new Category();
             category.setId(resultSet.getInt("id"));

                categoryRecords.add(category);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
//        adding to each category translations
        Iterator<Category> categoryIterator = categoryRecords.iterator();
        while (categoryIterator.hasNext()){
            Category category = categoryIterator.next();
            Category categoryFromDB = read(category.getId());

            category.setTranslations(categoryFromDB.getTranslations());
            category.setDeleted(categoryFromDB.isDeleted());
        }
        return categoryRecords;

    }

}
