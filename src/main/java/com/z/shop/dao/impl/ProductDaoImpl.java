package com.z.shop.dao.impl;

import com.z.shop.dao.ProductDao;
import com.z.shop.entity.Product;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProductDaoImpl implements ProductDao {
    private static final Logger LOGGER = LogManager.getLogger(ProductDaoImpl.class);

    private static final Lock CONNECTION_LOCK = new ReentrantLock();
    private static DBManager dbManager = DBManager.getInstance();

    private static String CREATE = "INSERT INTO product(name, image, category_id, quantity, description, color, scale, price, adding_date)"+
            " VALUE (?, ?, (SELECT id from category where name = ?), ?, ?, ?, ?, ?, ?)";

    private static String READ_ALL = "SELECT p.id, p.name, p.image, c.name AS category, p.quantity, p.description, p.color, p.scale, p.price, p.adding_date, p.deleted " +
            "FROM product p " +
            "JOIN category c ON p.category_id = c.id WHERE p.deleted = false";




    private static String READ_BY_ID = "SELECT p.id, p.name, p.image, c.name AS category, p.quantity, p.description, p.color, p.scale, p.price, p.adding_date, p.deleted "+
           "FROM product p " +
            "JOIN category c ON p.category_id = c.id " +
            "WHERE p.id =? ";

    private static String UPDATE_BY_ID = "UPDATE product SET name =?, image=?, category_id=(SELECT id from category where name = ?), quantity=?, description=?, color=?, scale=?, price=? WHERE id = ?";
    private static String DELETE_BY_ID = "UPDATE product SET deleted = true WHERE id =?";


    @Override
    public Product create(Product product) {
        try ( Connection connection = dbManager.getConnection();
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

        try ( Connection connection = dbManager.getConnection();
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

        try {connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);

            int k = 0;
            preparedStatement.setString(++k, product.getName());
            preparedStatement.setString(++k, product.getImage());
            preparedStatement.setString(++k, product.getCategory());
            preparedStatement.setInt(++k,product.getQuantity());
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
            DBManager.close(preparedStatement);
            DBManager.close(connection);
            CONNECTION_LOCK.unlock();
        }
        return product;
    }



    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CONNECTION_LOCK.lock();
        try { connection = dbManager.getConnection();
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
            DBManager.close(preparedStatement);
            DBManager.close(connection);
            CONNECTION_LOCK.unlock();
        }
    }

    @Override
    public List<Product> readAll() {
        List<Product> productRecords = new ArrayList<>();
        try ( Connection connection = dbManager.getConnection();
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

    private static Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
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
