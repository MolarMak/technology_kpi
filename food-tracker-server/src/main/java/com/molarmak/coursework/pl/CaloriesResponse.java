package com.molarmak.coursework.pl;

import com.molarmak.coursework.dal.db.FoodEat;

import java.util.List;

public class CaloriesResponse {
    private int calories;
    private boolean isOk;
    private List<FoodEat> eatenFood;

    public CaloriesResponse() { }

    public CaloriesResponse(int calories, boolean isOk, List<FoodEat> eatenFood) {
        this.calories = calories;
        this.isOk = isOk;
        this.eatenFood = eatenFood;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public List<FoodEat> getEatenFood() {
        return eatenFood;
    }

    public void setEatenFood(List<FoodEat> eatenFood) {
        this.eatenFood = eatenFood;
    }
}
