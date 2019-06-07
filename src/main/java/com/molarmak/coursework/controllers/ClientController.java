package com.molarmak.coursework.controllers;

import com.molarmak.coursework.entities.db.Client;
import com.molarmak.coursework.entities.rest.AuthRequest;
import com.molarmak.coursework.entities.rest.ClientDataRequest;
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

    private final ClientDataService repository;

    public ClientController(ClientDataService repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody AuthRequest request) {
        Client clientCheck = repository.findByEmail(request.getEmail());

        if(clientCheck != null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("This email already taken!");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        Client client = new Client(
                request.getEmail(),
                request.getPassword(),
                null,
                RandomStringUtils.randomAlphabetic(32),
                0,
                0.0,
                0.0,
                0
        );
        repository.save(client);
        ClientResponse response = new ClientResponse();
        response.setToken(client.getToken());

        return new ResponseEntity<>(new Response(response), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginByEmailAndPassword(@RequestBody AuthRequest request) {
        Client clientCheck = repository.findByEmailAndPassword(request.getEmail(), request.getPassword());

        if(clientCheck == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Email or password incorrect!");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        ClientResponse response = new ClientResponse();
        response.setToken(clientCheck.getToken());

        return new ResponseEntity<>(new Response(response), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Response> updateClientData(@RequestBody ClientDataRequest request) {
        Client clientCheck = repository.findByToken(request.getToken());

        if (clientCheck == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Token not valid");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        clientCheck.setName(request.getName());
        clientCheck.setAge(request.getAge());
        clientCheck.setHeight(request.getHeight());
        clientCheck.setWeight(request.getWeight());
        clientCheck.setLifeStyle(request.getLifeStyle());

        repository.save(clientCheck);
        return new ResponseEntity<>(new Response(), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Response> getClientByToken(@RequestParam String token) {
        Client client = repository.findByToken(token);

        if (client == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Token not valid");
            return new ResponseEntity<>(new Response(errors), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Response(client), HttpStatus.OK);
    }
}
