package com.molarmak.coursework.dal;

import com.molarmak.coursework.dal.db.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodDataService extends CrudRepository<Food,Long> {
    Food findById(long id);

    List<Food> findAll();

    List<Food> findByNameIgnoreCaseContaining(String name);
}
