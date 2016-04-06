package com.manuelmarpereira.decorator;

public abstract class Meal {

    String name = "Unknown Meal";

    public String getName() {

        return name;
    }

    public abstract Double price();


}
