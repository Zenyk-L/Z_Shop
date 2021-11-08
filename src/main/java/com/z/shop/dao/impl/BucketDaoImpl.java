package com.z.shop.dao.impl;

import com.z.shop.dao.BucketDao;
import com.z.shop.entity.Bucket;
import com.z.shop.utils.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BucketDaoImpl implements BucketDao {
    private static final Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);

    private static final Lock CONNECTION_LOCK = new ReentrantLock();

    private static final String READ_ALL = "SELECT * FROM z_shop.buckets WHERE deleted = false";
    private static final String CREATE = "INSERT INTO z_shop.buckets ( user_id, product_id, purchase_date) VALUES (?,?,?)";
    private static final String READ_BY_ID = "SELECT * FROM z_shop.buckets WHERE id =? AND deleted = false";
    private static final String DELETE_BY_ID = "UPDATE z_shop.buckets SET deleted = true WHERE id =?";


    @Override
    public Bucket create(Bucket bucket) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            preparedStatement.setInt(++k, bucket.getUserId());
            preparedStatement.setInt(++k, bucket.getProductId());
            preparedStatement.setDate(++k, new Date(bucket.getPurchaseDate().getTime()));
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
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Integer userId = resultSet.getInt("user_id");
            Integer productId = resultSet.getInt("product_id");
            Date purchaseDate = resultSet.getDate("purchase_date");

            bucket = new Bucket(id, userId, productId, purchaseDate);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        throw new IllegalStateException("There is no update for bucket");
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CONNECTION_LOCK.lock();
        try {
            connection = ConnectionManager.getConnection();
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
    public List<Bucket> readAll() {
        List<Bucket> bucketRecords = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer userId = resultSet.getInt("user_id");
                Integer productId = resultSet.getInt("product_id");
                Date purchaseDate = resultSet.getDate("purchase_date");

                bucketRecords.add(new Bucket(id, userId, productId, purchaseDate));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucketRecords;
    }
}
