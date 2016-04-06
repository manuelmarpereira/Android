package com.manuelmarpereira.decorator;

public class Chips extends ExtraDecorator{

    Meal meal;

    public Chips(Meal meal){

        this.meal = meal;
    } 

    @Override
    public String getName() {
        return meal.getName()+" + Chips";
    }

    @Override
    public Double price() {
        return meal.price()+1.5;
    }

}
