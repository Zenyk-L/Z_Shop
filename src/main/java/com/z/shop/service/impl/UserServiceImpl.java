package com.z.shop.service.impl;

import com.z.shop.dao.UserDao;
import com.z.shop.dao.impl.UserDaoImpl;
import com.z.shop.entity.User;
import com.z.shop.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    public  static UserService userServiceImpl;

    private UserDao userDao;

    private UserServiceImpl()  {
            userDao = new UserDaoImpl();
    }

    public  static UserService getUserService(){
        if (userServiceImpl == null)
            userServiceImpl = new UserServiceImpl();
        return userServiceImpl;
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) {
        return userDao.read(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
