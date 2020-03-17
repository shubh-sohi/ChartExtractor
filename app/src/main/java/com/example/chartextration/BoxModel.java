package com.example.chartextration;

import java.util.ArrayList;

public class BoxModel {
    ArrayList<Box> boxes;
    ArrayList<BoxListener> subscribers;

    public BoxModel() {
        boxes = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public void addBox(float x, float y, float w, float h) {
        this.boxes.add(new Box(x,y,w,h));
        notifySubscribers();
    }

    public boolean checkHit(float x, float y) {
        return boxes.stream().anyMatch(b -> b.contains(x,y));
    }

    public Box find(float x, float y) {
        Box found = null;
        for (Box b : boxes) {
            if (b.contains(x,y)) {
                found = b;
            }
        }
        return found;
    }

    public Box getLatestBox() {
        return boxes.get(boxes.size()-1);
    }

    public void resizeBox(Box b, float dx, float dy) {
        b.resize(dx,dy);
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
