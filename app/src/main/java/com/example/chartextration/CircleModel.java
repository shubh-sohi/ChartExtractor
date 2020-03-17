package com.example.chartextration;

import java.util.ArrayList;
import java.util.List;

public class CircleModel {
    ArrayList<Circle> circles;
    ArrayList<CircleListner> subscribers;

    public CircleModel(){
        circles = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public void addCircle(float x, float y){
        this.circles.add(new Circle(x,y));
        notifySubscribers();
    }

    public boolean checkHit(float x, float y){
        return circles.stream().anyMatch(b -> b.contains(x,y));
    }

    public Circle find(float x, float y){
        Circle found = null;
        for (Circle c : circles){
            if (c.contains(x,y)){
                found = c;
            }
        }
        return found;
    }

    public void moveCircle(List<Circle> circleList, float dx, float dy){
//        c.move(dx,dy);
        circleList.forEach(b -> b.move(dx, dy));
        notifySubscribers();
    }

    public void addSubscriber(CircleListner subscriber){
        subscribers.add(subscriber);
    }

    private void notifySubscribers(){
        for (CircleListner cl : subscribers){
            cl.modelChanged();
        }
    }
}
