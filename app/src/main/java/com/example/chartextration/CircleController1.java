package com.example.chartextration;

import android.view.MotionEvent;
import android.view.View;

public class CircleController1 extends CircleController implements View.OnTouchListener {
    CircleModel Cmodel;
    CircleInteractionModel CiModel;
    BoxModel Bmodel = new BoxModel();


    private enum State {READY, PENDING, DRAGGING}

    private State currentState = State.READY;

    private float normX, normY;
    private float normDX, normDY;
    private float prevNormX, prevNormY;

    public CircleController1() {
        prevNormX = 0;
        prevNormY = 0;
    }

    public void setModel(CircleModel aModel) {
        Cmodel = aModel;
    }

    public void setCIModel(CircleInteractionModel im) {
        CiModel = im;
    }

    public boolean onTouch(View v, MotionEvent event) {
        normX = event.getX() / CiModel.viewWidth;
        normY = event.getY() / CiModel.viewHeight;
        normDX = normX - prevNormX;
        normDY = normY - prevNormY;
        prevNormX = normX;
        prevNormY = normY;
        if(!Bmodel.checkHit(normX, normY)) {
            switch (currentState) {
                case READY:
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (!Cmodel.checkHit(normX, normY)) {
                                CiModel.clearSelection();
                                Cmodel.addCircle(normX, normY);

                                currentState = State.PENDING;
                            } else {
                                Circle c = Cmodel.find(normX, normY);
                                if (!CiModel.selectionSet.contains(c)) {
                                    CiModel.clearSelection();
                                    CiModel.addSelection(c);
                                }
                                currentState = State.DRAGGING;
                            }
                            break;
                    }
                    break;

                case PENDING:
                    switch (event.getAction()) {
                        // Event: touch up
                        case MotionEvent.ACTION_UP:
                            // Context: none
                            // Side Effects:
                            // Move to new state:
                            currentState = State.READY;
                            break;
                        // Event: touch move
//                    case MotionEvent.ACTION_MOVE:
//                        // Context: none
//                        // Side Effects:
//                        iModel.setRubberbandStart(normX, normY);
//                        // Move to new state:
//                        currentState = State.RUBBERBAND;
//                        break;
                    }
                    break;
                case DRAGGING:
                    switch (event.getAction()) {
                        // Event: touch move
                        case MotionEvent.ACTION_MOVE:
                            // Context: none
                            // Side Effects:
                            Cmodel.moveCircle(CiModel.selectionSet, normDX, normDY);
                            // Stay in the same state:
                            currentState = State.DRAGGING;
                            break;
                        case MotionEvent.ACTION_UP:
                            // Context: none
                            // Side Effects: none
                            // Change state
                            currentState = State.READY;



                    }
                    break;
            }
        }
        return true;
    }
}
