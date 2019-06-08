package com.molarmak.coursework.entities.db;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    public Client() {}

    public Client(String email, String password, String name, String token, int age, double height, double weight, int lifeStyle) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.token = token;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.lifeStyle = lifeStyle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column
    @JsonIgnore
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String name;

    @Column
    @JsonIgnore
    private String token;

    @Column
    private int age;

    @Column
    private double height;

    @Column
    private double weight;

    @Column
    private int lifeStyle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
