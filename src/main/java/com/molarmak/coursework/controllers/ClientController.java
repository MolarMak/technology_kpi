package com.molarmak.coursework.controllers;

import com.molarmak.coursework.entities.db.Client;
import com.molarmak.coursework.entities.rest.ClientRequest;
import com.molarmak.coursework.entities.rest.ClientResponse;
import com.molarmak.coursework.entities.rest.Response;
import com.molarmak.coursework.services.ClientDataService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientDataService repository;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody ClientRequest request) {
        Client clientCheck = repository.findByEmail(request.getEmail());

        if(clientCheck != null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("This email already taken!");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        Client client = new Client(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                RandomStringUtils.randomAlphabetic(32),
                request.getAge(),
                request.getHeight(),
                request.getWeight(),
                request.getLifeStyle()
        );
        repository.save(client);
        ClientResponse response = new ClientResponse();
        response.setToken(client.getToken());

        return new ResponseEntity<>(new Response(response), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<Response> getClientByToken(@RequestParam String token) {
        Client client = repository.findByToken(token);

        if (client == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Token not valid");
            return new ResponseEntity<>(new Response(errors), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Response(client), HttpStatus.OK);
    }
}
