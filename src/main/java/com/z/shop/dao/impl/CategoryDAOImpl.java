package com.z.shop.dao.impl;

import com.z.shop.dao.CategoryDAO;
import com.z.shop.entity.Category;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger LOGGER = LogManager.getLogger(CategoryDAOImpl.class);
    private static DBManager dbManager = DBManager.getInstance();

    private static String CREATE_CATEGORY = "INSERT INTO category (name) VALUE (?)";
    private static String READ_ALL = "SELECT * FROM category WHERE deleted = false";
    private static String READ_BY_ID = "SELECT * FROM category  WHERE id =? AND deleted = false";



    @Override
    public Category create(Category category) {
         DBManager dbManager = DBManager.getInstance();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            preparedStatement.setString(++k, category.getName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return category;
    }

    @Override
    public Category read(Integer id) {
        Category category = null;
        try ( Connection connection = dbManager.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID))  {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

                category = getCategoryFromResultSet(resultSet);

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
            List<Category> categoryRecords = new ArrayList<>();
            try ( Connection connection = dbManager.getConnection();
                  PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL))  {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){

                    Category category = getCategoryFromResultSet(resultSet);
                    categoryRecords.add(category);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            return categoryRecords;

    }

    private static Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        boolean deleted = resultSet.getBoolean("deleted");

        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDeleted(deleted);
        return  category;
    }
}
