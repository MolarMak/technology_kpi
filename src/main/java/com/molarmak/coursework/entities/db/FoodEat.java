package com.molarmak.coursework.entities.db;

import javax.persistence.*;

@Entity
@Table(name = "food_eat")
public class FoodEat {

    public FoodEat() {}

    public FoodEat(long clientId, long foodId) {
        this.clientId = clientId;
        this.foodId = foodId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long clientId;

    @Column
    private long foodId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }
}
