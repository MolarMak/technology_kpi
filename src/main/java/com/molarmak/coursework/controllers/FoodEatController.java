package com.molarmak.coursework.controllers;

import com.molarmak.coursework.entities.db.Client;
import com.molarmak.coursework.entities.db.Food;
import com.molarmak.coursework.entities.db.FoodEat;
import com.molarmak.coursework.entities.rest.CaloriesResponse;
import com.molarmak.coursework.entities.rest.ClientEatRequest;
import com.molarmak.coursework.entities.rest.Response;
import com.molarmak.coursework.services.ClientDataService;
import com.molarmak.coursework.services.FoodDataService;
import com.molarmak.coursework.services.FoodEatDataService;
import org.springframework.beans.factory.annotation.Autowired;
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

        return new ResponseEntity<>(new Response(null), HttpStatus.OK);
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

        int calories = 0;
        for(FoodEat fe: eatenByClient) {
            calories += fe.getFood().getCarbohydrates() + fe.getFood().getFats() + fe.getFood().getProtein();
        }

        CaloriesResponse response = new CaloriesResponse(calories, true, eatenByClient);
        return new ResponseEntity<>(new Response(response), HttpStatus.OK);
    }

}
