package com.greenloop.service;

import com.greenloop.dao.UserDAO;
import com.greenloop.dao.UserDAOImpl;
import com.greenloop.model.Role;
import com.greenloop.model.User;
import com.greenloop.util.PasswordUtil;

public class AuthService {
    private final UserDAO userDAO = new UserDAOImpl();

    public User login(String email, String password) throws Exception {
        User u = userDAO.findByEmail(email);
        if(u==null) return null;
        String hash = PasswordUtil.hash(password, u.getSalt());
        return hash.equals(u.getPasswordHash()) ? u : null;
    }

    public int register(String name, String email, String password, Role role) throws Exception {
        String salt = PasswordUtil.genSalt();
        String hash = PasswordUtil.hash(password, salt);
        return userDAO.create(name, email, hash, salt, role);
    }
}
