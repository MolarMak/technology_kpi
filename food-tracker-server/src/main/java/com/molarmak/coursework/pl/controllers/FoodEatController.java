package com.molarmak.coursework.pl.controllers;

import com.molarmak.coursework.dal.db.Client;
import com.molarmak.coursework.dal.db.Food;
import com.molarmak.coursework.dal.db.FoodEat;
import com.molarmak.coursework.pl.CaloriesResponse;
import com.molarmak.coursework.pl.ClientEatRequest;
import com.molarmak.coursework.pl.Response;
import com.molarmak.coursework.dal.ClientDataService;
import com.molarmak.coursework.dal.FoodDataService;
import com.molarmak.coursework.dal.FoodEatDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/eat")
public class FoodEatController {

    private final FoodEatDataService foodEatRepository;

    private final ClientDataService clientRepository;

    private final FoodDataService foodRepository;

    public FoodEatController(FoodEatDataService foodEatRepository, ClientDataService clientRepository, FoodDataService foodRepository) {
        this.foodEatRepository = foodEatRepository;
        this.clientRepository = clientRepository;
        this.foodRepository = foodRepository;
    }

    @PostMapping("/new")
    public ResponseEntity<Response> clientEatFood(@RequestBody ClientEatRequest request) {
        Client client = clientRepository.findByToken(request.getToken());
        Food food = foodRepository.findById(request.getFoodId());

        if(client == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Token not valid");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        if(food == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Can't find the food!");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        FoodEat foodEat = new FoodEat(client, food);
        foodEatRepository.save(foodEat);

        return new ResponseEntity<>(new Response(), HttpStatus.OK);
    }

    @GetMapping("/calories")
    public ResponseEntity<Response> getCaloriesByClient(@RequestParam String token) {
        Client client = clientRepository.findByToken(token);

        if(client == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Token not valid");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        List<FoodEat> eatenByClient = foodEatRepository.findAllByClientId(client.getId());

        double possibleCalories =
                ((200 - client.getAge()) *
                        (200 - client.getWeight()) *
                        ((250 - client.getHeight()) * 0.01) *
                        (client.getLifeStyle())) / 10;

        int calories = 0;
        for(FoodEat fe: eatenByClient) {
            Food food = fe.getFood();
            calories += food.getProtein() * 0.25 + food.getFats() * 0.5 + food.getCarbohydrates() * 0.1;
        }
        System.out.println(possibleCalories);

        CaloriesResponse response = new CaloriesResponse(calories, possibleCalories >= calories, eatenByClient);
        return new ResponseEntity<>(new Response(response), HttpStatus.OK);
    }

}
