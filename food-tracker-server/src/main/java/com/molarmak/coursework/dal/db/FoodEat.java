package com.molarmak.coursework.dal.db;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "food_eat")
public class FoodEat {

    public FoodEat() {}

    public FoodEat(Client client, Food food) {
        this.client = client;
        this.food = food;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIgnore
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private Food food;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
