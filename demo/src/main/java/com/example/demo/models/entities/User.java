package com.example.demo.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    //o	The length of the values should be between 3 and 10 characters long (both numbers are INCLUSIVE)
    //o	The values should be unique in the database

    @Column(name = "full_name", nullable = false)
    private String fullName;
    //o	The length of the values should be between 5 and 20 characters long (both numbers are INCLUSIVE)

    @Column (nullable = false)
    private String password;
    //o	The length of the values should be more than 3 characters long (INCLUSIVE)

    @Column (nullable = false, unique = true)
    private String email;
    //•	Email
    //o	The values should contain a '@' symbol
    //o	The values should be unique in the database


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
