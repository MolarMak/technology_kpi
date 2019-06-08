package com.molarmak.coursework.pl;

public class ClientDataRequest {

    private String token;

    private String name;

    private int age;

    private double height;

    private double weight;

    private int lifeStyle;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(int lifeStyle) {
        this.lifeStyle = lifeStyle;
    }
}
