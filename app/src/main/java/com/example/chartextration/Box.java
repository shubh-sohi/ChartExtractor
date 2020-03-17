package com.example.chartextration;

public class Box {
    float left, top, width, height;

    public Box(float newLeft, float newTop, float newWidth,float newHeight ){
        left = newLeft;
        top = newTop;
        width = newWidth;
        height = newHeight;
    }

    public boolean contains(float x, float y) {
        return (x >= left && x <= left+width && y >= top && y <= top+height);
    }

    public void resize(float dx, float dy) {
        width += dx;
        height += dy;
    }

}
