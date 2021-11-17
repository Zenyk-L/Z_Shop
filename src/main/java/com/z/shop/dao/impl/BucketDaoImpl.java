package com.z.shop.dao.impl;

import com.z.shop.dao.OrderDAO;
import com.z.shop.entity.Order;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BucketDaoImpl implements OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(BucketDaoImpl.class);

    private static final Lock CONNECTION_LOCK = new ReentrantLock();

    private static final String READ_ALL = "SELECT * FROM z_shop.buckets WHERE deleted = false";
    private static final String CREATE = "INSERT INTO z_shop.buckets ( user_id, product_id, purchase_date, status) VALUES (?,?,?,?)";
    private static final String READ_BY_ID = "SELECT * FROM z_shop.buckets WHERE id =? AND deleted = false";
    private static final String DELETE_BY_ID = "UPDATE z_shop.buckets SET deleted = true WHERE id =?";


    @Override
    public Order create(Order order) {
        DBManager dbManager = DBManager.getInstance();
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            preparedStatement.setInt(++k, order.getUserId());
            preparedStatement.setInt(++k, order.getProductId());
            preparedStatement.setDate(++k, new Date(order.getPurchaseDate().getTime()));
            preparedStatement.setString(++k, order.getStatus());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            order.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return order;
    }

    @Override
    public Order read(Integer id) {
        Order order = null;
        DBManager dbManager = DBManager.getInstance();
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            order = getBucketFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        throw new IllegalStateException("There is no update for bucket");
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CONNECTION_LOCK.lock();
        DBManager dbManager = DBManager.getInstance();
        try {connection = dbManager.getConnection();
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
    public List<Order> readAll() {
        List<Order> orderRecords = new ArrayList<>();
        DBManager dbManager = DBManager.getInstance();
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Order order = getBucketFromResultSet(resultSet);
                orderRecords.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return orderRecords;
    }

    private Order getBucketFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer userId = resultSet.getInt("user_id");
        Integer productId = resultSet.getInt("product_id");
        Date purchaseDate = resultSet.getDate("purchase_date");
        String status = resultSet.getString("status");

        Order order = new Order(id, userId, productId, purchaseDate, status);
        return order;
    }
}
