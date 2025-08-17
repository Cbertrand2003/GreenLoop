package com.greenloop.dao;

import com.greenloop.model.Role;
import com.greenloop.model.User;

public interface UserDAO {
    User findByEmail(String email) throws Exception;
    User findById(int id) throws Exception;
    int create(String name, String email, String passwordHash, String salt, Role role) throws Exception; // returns new id
}
