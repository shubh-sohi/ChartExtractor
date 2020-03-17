package com.example.chartextration;

import android.view.View;

abstract class CircleController implements View.OnTouchListener {
    CircleModel Cmodel;
    CircleInteractionModel Cimodel;
    public void setModel(CircleModel CaModel){
        Cmodel = CaModel;
    }
    public void setCIModel(CircleInteractionModel Cim){ Cimodel = Cim;}
}
