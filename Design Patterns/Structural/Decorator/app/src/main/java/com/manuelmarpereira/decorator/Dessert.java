package com.manuelmarpereira.decorator;

public class Dessert extends ExtraDecorator{

    Meal meal;

    public Dessert(Meal meal){

        this.meal= meal;
    }

    @Override
    public Double price() {

        return meal.price()+3.0;
    }

    @Override
    public String getName() {
       return meal.getName()+" + Dessert";
    }


}
