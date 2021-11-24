package com.z.shop.dao.impl;

import com.z.shop.dao.BucketDAO;
import com.z.shop.entity.Bucket;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BucketDaoImpl implements BucketDAO {
    private static final Logger LOGGER = LogManager.getLogger(BucketDaoImpl.class);
    private static final DBManager dbManager = DBManager.getInstance();

    private static final Lock CONNECTION_LOCK = new ReentrantLock();

    private static final String READ_ALL = "SELECT * FROM bucket WHERE deleted = false";
    private static final String CREATE = "INSERT INTO bucket ( user_id, product_id, quantity, purchase_date, status) VALUES (?,?,?,?,?)";
    private static final String READ_BY_ID = "SELECT * FROM bucket WHERE id =? AND deleted = false";
    private static String UPDATE_BY_ID = "UPDATE bucket SET user_id =?, product_id=?, quantity=?, purchase_date=?, status=? WHERE id = ?";

    private static final String DELETE_BY_ID = "UPDATE bucket SET deleted = true WHERE id =?";


    @Override
    public Bucket create(Bucket bucket) {
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            preparedStatement.setInt(++k, bucket.getUserId());
            preparedStatement.setInt(++k, bucket.getProductId());
            preparedStatement.setInt(++k, bucket.getQuantity());
            preparedStatement.setDate(++k, new Date(bucket.getPurchaseDate().getTime()));
            preparedStatement.setString(++k, bucket.getStatus());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            bucket.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucket;
    }

    @Override
    public Bucket read(Integer id) {
        Bucket bucket = null;
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            bucket = getBucketFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        CONNECTION_LOCK.lock();

        try {
            connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);

            int k = 0;
            preparedStatement.setInt(++k, bucket.getUserId());
            preparedStatement.setInt(++k, bucket.getProductId());
            preparedStatement.setInt(++k, bucket.getQuantity());
            preparedStatement.setDate(++k, new Date(bucket.getPurchaseDate().getTime()));
            preparedStatement.setString(++k, bucket.getStatus());
            preparedStatement.setInt(++k, bucket.getId());

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
        return bucket;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CONNECTION_LOCK.lock();
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
    public List<Bucket> readAll() {
        List<Bucket> bucketRecords = new ArrayList<>();
        try ( Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Bucket bucket = getBucketFromResultSet(resultSet);
                bucketRecords.add(bucket);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucketRecords;
    }

    private Bucket getBucketFromResultSet(ResultSet resultSet) throws SQLException {
        Bucket bucket = new Bucket();
        Integer id = resultSet.getInt("id");
        Integer userId = resultSet.getInt("user_id");
        Integer productId = resultSet.getInt("product_id");
        Integer quantity = resultSet.getInt("quantity");
        Date purchaseDate = resultSet.getDate("purchase_date");
        String status = resultSet.getString("status");
        boolean deleted = resultSet.getBoolean("deleted");

        bucket.setId(id);
        bucket.setUserId(userId);
        bucket.setProductId(productId);
        bucket.setQuantity(quantity);
        bucket.setPurchaseDate(purchaseDate);
        bucket.setStatus(status);
        bucket.setDeleted(deleted);

        return bucket;
    }
}
