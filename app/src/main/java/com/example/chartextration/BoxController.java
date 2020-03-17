package com.example.chartextration;

import android.view.View;

abstract class BoxController implements View.OnTouchListener {
    BoxModel model;
    InteractionModel iModel;
    public void setModel(BoxModel aModel) {
        model = aModel;
    }
    public void setIModel(InteractionModel im) { iModel = im; }
}

