package com.manuelmarpereira.decorator;

/**
 *
 *
 */

public class Pizza extends Meal {

    private double price=0.0;

    public Pizza(String name, double price){
        super.name=name;
        this.price=price;
    }

    @Override
    public Double price() {
        return price;
    }

}
