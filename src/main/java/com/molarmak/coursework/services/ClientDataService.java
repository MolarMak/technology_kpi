package com.molarmak.coursework.services;

import com.molarmak.coursework.entities.db.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientDataService extends CrudRepository<Client,Long> {
    Client findByEmail(String email);

    Client findByToken(String token);

    List<Client> findAll();
}
