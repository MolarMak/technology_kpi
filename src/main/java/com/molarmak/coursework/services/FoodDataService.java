package com.molarmak.coursework.services;

import com.molarmak.coursework.entities.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodDataService extends CrudRepository<Food,Long> {
    Food findById(long id);

    Food findByName(String name);

    List<Food> findAll();
}
