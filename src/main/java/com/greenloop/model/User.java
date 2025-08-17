package com.greenloop.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String passwordHash;
    private String salt;
    private Role role;

    public User() {}

    public User(int id, String name, String email, String passwordHash, String salt, Role role) {
        this.id = id; this.name = name; this.email = email;
        this.passwordHash = passwordHash; this.salt = salt; this.role = role;
    }
    // getters/setters
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public String getPasswordHash(){return passwordHash;}
    public void setPasswordHash(String passwordHash){this.passwordHash=passwordHash;}
    public String getSalt(){return salt;}
    public void setSalt(String salt){this.salt=salt;}
    public Role getRole(){return role;}
    public void setRole(Role role){this.role=role;}
}
