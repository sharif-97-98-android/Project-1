package com.example.myapplication;

import com.example.myapplication.Subject;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class NotificationCenter extends Observable {
    ArrayList<Observer> observers;
    public NotificationCenter(){
        observers = new ArrayList<>();
    }
    //@Override
    public void data_loaded(int[] message){
        for(Observer element:observers)
            element.update(this, message);

    }
    //@Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    //@Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }


}
