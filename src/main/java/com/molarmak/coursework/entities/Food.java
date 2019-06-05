package com.molarmak.coursework.entities;

import javax.persistence.*;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column
    public String name;

    @Column
    public int protein;

    @Column
    public int fats;

    @Column
    public int carbohydrates;
}
