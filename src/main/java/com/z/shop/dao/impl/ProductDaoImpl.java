package com.z.shop.dao.impl;

import com.z.shop.dao.ProductDao;
import com.z.shop.entity.Product;
import com.z.shop.utils.DBManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProductDaoImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

    private static final Lock CONNECTION_LOCK = new ReentrantLock();

    private static String READ_ALL = "SELECT * FROM z_shop.products WHERE deleted = false";
    private static String CREATE = "INSERT INTO z_shop.products (name, image, category, quantity, description, color, scale, price, adding_date) VALUES (?,?,?,?,?,?,?,?,?)";
    private static String READ_BY_ID = "SELECT * FROM z_shop.products WHERE id =? AND deleted = false";
    private static String UPDATE_BY_ID = "UPDATE z_shop.products SET name =?, image=?, category=?, quantity=?, description=?, color=?, scale=?, price=? WHERE id = ?";
    private static String DELETE_BY_ID = "UPDATE z_shop.products SET deleted = true WHERE id =?";


    @Override
    public Product create(Product product) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            preparedStatement.setString(++k, product.getName());
            preparedStatement.setString(++k, product.getImage());
            preparedStatement.setString(++k, product.getCategory());
            preparedStatement.setInt(++k, product.getQuantity());
            preparedStatement.setString(++k, product.getDescription());
            preparedStatement.setString(++k, product.getColor());
            preparedStatement.setString(++k, product.getScale());
            preparedStatement.setDouble(++k, product.getPrice());
            preparedStatement.setDate(++k, new Date(product.getAddingDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }

    @Override
    public Product read(Integer id) {
        Product product = null;
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            product = getProductFromResultSet(resultSet);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CONNECTION_LOCK.lock();
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);

            int k = 0;
            preparedStatement.setString(++k, product.getName());
            preparedStatement.setString(++k, product.getImage());
            preparedStatement.setString(++k, product.getCategory());
            preparedStatement.setString(++k, product.getDescription());
            preparedStatement.setString(++k, product.getColor());
            preparedStatement.setString(++k, product.getScale());
            preparedStatement.setDouble(++k, product.getPrice());
            preparedStatement.setInt(++k, product.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(e);
                }
            }
            LOGGER.error(e);
        } finally {
            close(preparedStatement);
            close(connection);
            CONNECTION_LOCK.unlock();
        }
        return product;
    }

    private void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CONNECTION_LOCK.lock();
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(ex);
                }
            }
            LOGGER.error(e);
        } finally {
            close(preparedStatement);
            close(connection);
            CONNECTION_LOCK.unlock();
        }
    }

    @Override
    public List<Product> readAll() {
        List<Product> productRecords = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL))  {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                Product product= getProductFromResultSet(resultSet);
                productRecords.add(product);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return productRecords;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String image = resultSet.getString("image");
        String category = resultSet.getString("category");
        int quantity = resultSet.getInt("quantity");
        String description = resultSet.getString("description");
        String color = resultSet.getString("color");
        String scale = resultSet.getString("scale");
        Double price = resultSet.getDouble("price");
        Date date = resultSet.getDate("adding_date");
        Boolean deleted = resultSet.getBoolean("deleted");
        return new Product(id, name, image, category, quantity, description, color, scale, price, date, deleted);
    }
}
