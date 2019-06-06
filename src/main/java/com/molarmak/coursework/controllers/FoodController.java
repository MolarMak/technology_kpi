package com.molarmak.coursework.controllers;

import com.molarmak.coursework.entities.db.Food;
import com.molarmak.coursework.entities.rest.Response;
import com.molarmak.coursework.services.FoodDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodDataService repository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> create(@RequestBody Food food) {
        Food checkFood = repository.findById(food.getId());

        if(checkFood != null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("This food already exists!");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response(repository.save(food)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response> getFoodById(@PathVariable("id") long id) {
        Food food = repository.findById(id);

        if (food == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Can't find food by this id");
            return new ResponseEntity<>(new Response(errors), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Response(food), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response> getAllFoods() {
        List<Food> foodList = repository.findAll();
        return new ResponseEntity<>(new Response(foodList), HttpStatus.OK);
    }

}
