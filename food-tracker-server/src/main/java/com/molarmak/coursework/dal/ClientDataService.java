package com.molarmak.coursework.dal;

import com.molarmak.coursework.dal.db.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientDataService extends CrudRepository<Client,Long> {
    Client findByEmail(String email);

    Client findByEmailAndPassword(String email, String password);

    Client findByToken(String token);

    List<Client> findAll();
}
