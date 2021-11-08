package com.z.shop.dao;

import com.z.shop.entity.User;
import com.z.shop.shared.AbstractCRUD;

public interface UserDao extends AbstractCRUD<User> {
    User getUserByEmail(String email);
}
