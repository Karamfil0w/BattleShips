package com.example.demo.models.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;
    // •	Name
    //o	The length of the values must be between 2 and 10 characters (both numbers are INCLUSIVE)
    //o	The values should be unique in the database

    private long health;
    //•	Health
    //o	The values should be positive numbers

    private long power;
    //•	Power
    //o	The values should be positive numbers
    @Column(nullable = false)
    private LocalDate created;
    //•	Created
    //o	The values should not be future dates
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Ship() {
    }

    public Category getCategory() {
        return category;
    }
    //•	Category
    //o	A relationship with the Categories Entity

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }
    //•	User
    //o	A user that owns the ship

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
