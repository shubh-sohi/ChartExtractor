package com.example.chartextration;

public class Circle {
    float x, y;

    public Circle(float new_x, float new_y){
        x = new_x;
        y = new_y;
    }

    public boolean contains(float point_x, float point_y){
        return (point_x <= x+0.01 && point_x >= x-0.01 && point_y <= y+0.01 && point_y >= y-0.01);
    }

    public void move(float dx, float dy){
        x += dx;
        y += dy;
    }
}
