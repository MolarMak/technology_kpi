package com.molarmak.coursework;

import com.molarmak.coursework.dal.db.Food;
import com.molarmak.coursework.dal.FoodDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class CourseworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseworkApplication.class, args);
    }

    @Bean
    CommandLineRunner init (FoodDataService repository){
        return args -> {
            ArrayList<Food> foods = new ArrayList<Food>();
            foods.add(new Food("Butter", 100, 20, 30));
            foods.add(new Food("Brod", 120, 150, 40));
            foods.forEach(repository::save);
        };
    }

}
