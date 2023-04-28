package com.example.demo.models.entities;

import com.example.demo.models.enums.ShipType;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ShipType name;
    //•	Name
    //o	An option between BATTLE, CARGO, PATROL
    //o	The values should be unique in the database

    @Column(columnDefinition = "TEXT")
    private String description;
    //•	Description
    //o	A very long and detailed description of the category
    //o	Can accept null values


    public Category() {
    }

    public Category(ShipType name) {
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShipType getName() {
        return name;
    }

    public void setName(ShipType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
