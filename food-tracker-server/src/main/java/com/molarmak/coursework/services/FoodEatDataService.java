package com.molarmak.coursework.services;

import com.molarmak.coursework.entities.db.FoodEat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodEatDataService extends CrudRepository<FoodEat,Long> {

    List<FoodEat> findAll();

    List<FoodEat> findAllByClientId(long clientId);
}
