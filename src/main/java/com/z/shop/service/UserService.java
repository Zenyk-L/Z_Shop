package com.z.shop.service;

import com.z.shop.entity.User;
import com.z.shop.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD<User> {
    User getUserByEmail(String email);
}
