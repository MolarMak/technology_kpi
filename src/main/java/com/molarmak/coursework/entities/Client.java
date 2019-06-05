package com.molarmak.coursework.entities;

import javax.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private double height;

    @Column
    private double weight;

    @Column
    private int lifeStyle;
}
