package com.manuelmarpereira.observer;

import java.util.*;

/**
 * Created by manuel on 3/15/16.
 */
public class TeamSubject {


    private List<Observer> observers = new ArrayList<Observer>();
    private String name;

    TeamSubject(String name){
        this.name =name;

    }

    public void setGoal(){
        notifyAllObservers();
    }

    public String getName(){
        return name;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void remove(Observer observer){
        observers.remove(observer);
    }

    private void notifyAllObservers() {
        for (Observer observer : observers){
            observer.update();
        }
    }
}
