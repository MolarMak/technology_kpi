package com.molarmak.coursework.pl.controllers;

import com.molarmak.coursework.dal.db.Food;
import com.molarmak.coursework.pl.FoodResponse;
import com.molarmak.coursework.pl.Response;
import com.molarmak.coursework.dal.FoodDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodDataService repository;

    public FoodController(FoodDataService repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> create(@RequestBody Food food) {
        Food checkFood = repository.findById(food.getId());

        if(checkFood != null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("This food already exists!");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response(repository.save(food)), HttpStatus.OK);
    }

    @GetMapping(value = "/info/{id}")
    public ResponseEntity<Response> getFoodById(@PathVariable("id") long id) {
        Food food = repository.findById(id);

        if (food == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Can't find food by this id");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Response(food), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Response> getAllFoods(@RequestParam String foodName) {
        if(foodName.isEmpty()) {
            List<Food> foodList = repository.findAll();
            return new ResponseEntity<>(new Response(new FoodResponse(foodList)), HttpStatus.OK);
        } else {
            List<Food> foodList = repository.findByNameIgnoreCaseContaining(foodName);
            return new ResponseEntity<>(new Response(new FoodResponse(foodList)), HttpStatus.OK);
        }
    }

}
