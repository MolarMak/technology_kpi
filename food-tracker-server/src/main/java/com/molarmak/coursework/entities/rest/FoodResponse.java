package com.molarmak.coursework.entities.rest;

import com.molarmak.coursework.entities.db.Food;

import java.util.List;

public class FoodResponse {

    private List<Food> foods;

    public FoodResponse() {}

    public FoodResponse(List<Food> foods) {
        this.foods = foods;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
