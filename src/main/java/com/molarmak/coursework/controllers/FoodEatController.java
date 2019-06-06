package com.molarmak.coursework.controllers;

import com.molarmak.coursework.entities.db.Client;
import com.molarmak.coursework.entities.db.Food;
import com.molarmak.coursework.entities.db.FoodEat;
import com.molarmak.coursework.entities.rest.ClientEatRequest;
import com.molarmak.coursework.entities.rest.Response;
import com.molarmak.coursework.services.ClientDataService;
import com.molarmak.coursework.services.FoodDataService;
import com.molarmak.coursework.services.FoodEatDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/eat")
public class FoodEatController {

    @Autowired
    private FoodEatDataService foodEatRepository;

    @Autowired
    private ClientDataService clientRepository;

    @Autowired
    private FoodDataService foodRepository;

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

        FoodEat foodEat = new FoodEat(client.getId(), food.getId());
        foodEatRepository.save(foodEat);

        return new ResponseEntity<>(new Response(null), HttpStatus.OK);
    }

}
