package com.molarmak.coursework.pl;

public class ClientEatRequest {

    private String token;
    private int foodId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
