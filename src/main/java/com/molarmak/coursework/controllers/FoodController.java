package com.molarmak.coursework.controllers;

import com.molarmak.coursework.entities.Food;
import com.molarmak.coursework.services.FoodDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodDataService repository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Food food) {
        Food checkFood = repository.findById(food.id);

        if(checkFood != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(repository.save(food), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Food> getFoodById(@PathVariable("id") long id) {
        Food food = repository.findById(id);

        if (food == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foodList = repository.findAll();
        if (foodList == null || foodList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

}
