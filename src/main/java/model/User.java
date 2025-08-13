package model;

import utils.PasswordUtil;

public class User {
    private final String username;
    private final String passwordHash;
    private final String role;
    private final boolean isActive;
    private final String email;

    public User(String username, String passwordHash, String role, String email, boolean isActive) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.username = username.trim().toLowerCase();
        this.passwordHash = passwordHash;
        this.role = role.toUpperCase();
        this.email = email;
        this.isActive = isActive;
    }

    // Getters
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public boolean isActive() { return isActive; }

    public boolean verifyPassword(String inputPassword) {
        return PasswordUtil.verify(inputPassword, passwordHash);
    }

    @Override
    public String toString() {
        return String.format("User[%s, Role: %s, Email: %s, Active: %b]", 
            username, role, email, isActive);
    }
}