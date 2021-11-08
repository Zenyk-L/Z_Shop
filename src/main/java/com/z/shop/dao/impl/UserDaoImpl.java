package com.z.shop.dao.impl;

import com.z.shop.dao.UserDao;
import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.utils.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final Lock CONNECTION_LOCK = new ReentrantLock();

    private static final String READ_ALL = "SELECT * FROM z_shop.users";
    private static final String CREATE = "INSERT INTO z_shop.users (email, name, last_name, password, role, amount) VALUES (?,?,?,?,?,?)";
    private static final String READ_BY_ID = "SELECT * FROM z_shop.users WHERE id =?";
    private static final String READ_BY_EMAIL = "SELECT * FROM z_shop.users WHERE email =?";
    private static final String UPDATE_BY_ID = "UPDATE z_shop.users SET email =?, name = ?, last_name = ?, role =?, password =?, amount=? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM z_shop.users WHERE id =?";

    @Override
    public User create(User user) {
        try ( Connection connection = ConnectionManager.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)){

            int k = 0;
            preparedStatement.setString(++k, user.getEmail());
            preparedStatement.setString(++k, user.getName());
            preparedStatement.setString(++k, user.getLastName());
            preparedStatement.setString(++k, user.getPassword());
            preparedStatement.setString(++k, user.getRole().toString());
            preparedStatement.setDouble(++k, user.getAmount());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try ( Connection connection = ConnectionManager.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CONNECTION_LOCK.lock();
        try {
           connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);

            int k = 0;
            preparedStatement.setString(++k, user.getEmail());
            preparedStatement.setString(++k, user.getName());
            preparedStatement.setString(++k, user.getLastName());
            preparedStatement.setString(++k, user.getRole().toString());
            preparedStatement.setString(++k, user.getPassword());
            preparedStatement.setDouble(++k,user.getAmount());
            preparedStatement.setInt(++k, user.getId());
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
        return user;
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
        try  ( Connection connection = ConnectionManager.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> userRecords = new ArrayList<>();
        try ( Connection connection = ConnectionManager.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);
              ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){

                userRecords.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return userRecords;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try ( Connection connection = ConnectionManager.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_EMAIL)){

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Integer userId = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("last_name");
        String role = resultSet.getString("role");
        String password = resultSet.getString("password");
        Double amount = resultSet.getDouble("amount");

        return new User(userId, name,lastName,email,password, UserRole.valueOf(role),amount) ;
    }


}
