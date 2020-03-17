package com.example.chartextration;

import java.util.ArrayList;
import java.util.List;

public class CircleInteractionModel {
    ArrayList<CircleListner> subscribers;
    float viewWidth, viewHeight;
    Circle selectedCircle;
    List<Circle> selectionSet;

    public CircleInteractionModel() {
        subscribers = new ArrayList<>();
        selectionSet = new ArrayList<>();
        selectedCircle = null;
    }

    public void setViewSize(float width, float height) {
        viewWidth = width;
        viewHeight = height;
    }

    public void addSelection(Circle c) {
        selectionSet.add(c);
    }

    public void clearSelection() {
        selectionSet.clear();
        notifySubscribers();
    }

    public void addSubscriber(CircleListner subscriber) {
        subscribers.add(subscriber);
    }

    private void notifySubscribers() {
        for (CircleListner cl : subscribers) {
            cl.modelChanged();
        }
    }
}
