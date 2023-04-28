package com.example.demo.repositories;

import com.example.demo.models.entities.Category;
import com.example.demo.models.entities.Ship;
import com.example.demo.models.enums.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(ShipType name);
}

