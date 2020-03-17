package com.example.chartextration;

import java.util.ArrayList;

public class InteractionModel {
    ArrayList<BoxListener> subscribers;
    float viewWidth, viewHeight;
    Box selectedBox;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        selectedBox = null;
    }

    public void setViewSize(float width, float height) {
        viewWidth = width;
        viewHeight = height;
    }

    public void setSelected(Box b) {
        selectedBox = b;
        notifySubscribers();
    }

    public void addSubscriber(BoxListener subscriber) {
        subscribers.add(subscriber);
    }

    private void notifySubscribers() {
        for (BoxListener sl : subscribers) {
            sl.modelChanged();
        }
    }
}
