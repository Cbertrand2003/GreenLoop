package model;

public class User {
    private String username;
    private String password;
    private String role;
    private boolean isActive;
    private String email; // Added field

    // 
    public User(String username, String password, String role, String email, boolean isActive) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.isActive = isActive;
    }

    // Add getter for email
    public String getEmail() {
        return email;
    }

    // Getter for isActive
    public boolean isActive() {
        return isActive;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    

    public String getRole() {
        return role;
    }

    public boolean verifyPassword(String inputPassword) {
        return password.equals(inputPassword); // plain comparison
    }

    @Override
    public String toString() {
        return String.format("User[%s, Role: %s, Active: %b]", username, role, isActive);
    }
}
