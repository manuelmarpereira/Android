package com.manuelmarpereira.decorator;


public class Drink extends ExtraDecorator{

    Meal meal;

    public Drink(Meal meal) {

        this.meal = meal;

    }

    @Override
    public String getName() {

        return meal.getName()+" + Drink";
    }

    @Override
    public Double price() {

        return meal.price()+2.0;
    }
}
