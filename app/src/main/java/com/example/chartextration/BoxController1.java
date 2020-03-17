package com.example.chartextration;

import android.view.MotionEvent;
import android.view.View;

public class BoxController1 extends BoxController implements View.OnTouchListener{
    BoxModel model;
    InteractionModel iModel;

    private enum State {READY, CREATING, DRAGGING};
    private State currentState = State.READY;

    private float normX, normY;
    private float normDX, normDY;
    private float prevNormX, prevNormY;

    public BoxController1() {
        prevNormX = 0;
        prevNormY = 0;
    }

    public void setModel(BoxModel aModel) {
        model = aModel;

    }

    public void setIModel(InteractionModel im) {
        iModel = im;
    }

    public boolean onTouch(View v, MotionEvent event) {
        normX = event.getX() / iModel.viewWidth;
        normY = event.getY() / iModel.viewHeight;
        normDX = normX - prevNormX;
        normDY = normY - prevNormY;
        prevNormX = normX;
        prevNormY = normY;

        if(model.boxes.size() == 1) {
            switch (currentState) {
                case READY:
                    switch (event.getAction()) {
                        // Event: touch down
                        case MotionEvent.ACTION_DOWN:
                            // Context: on background?
                            if (!model.checkHit(normX, normY)) {
                                // Side Effects:
                                if (model.boxes.size() < 1) {
//                                model.addBox(normX, normY, 0, 0);
                                    iModel.setSelected(model.getLatestBox());
                                    // Move to new state:
                                    currentState = State.CREATING;
                                }

                            } else {
                                // Context: on box
                                // Side Effects:
                                Box b = model.find(normX, normY);
                                iModel.setSelected(b);
                                // Move to new state:
                                currentState = State.DRAGGING;
                            }
                            break;
                    }
                    break;
                case CREATING:
                    switch (event.getAction()) {
                        // Event: touch up
                        case MotionEvent.ACTION_UP:
                            // Context: none
                            // Side Effects: none
                            // Move to new state:
                            currentState = State.READY;
                            break;
                        // Event: touch move
                        case MotionEvent.ACTION_MOVE:
                            // Context: none
                            // Side Effects:
                            model.resizeBox(iModel.selectedBox, normDX, normDY);
                            // stay in this state:
                            currentState = State.CREATING;
                            break;
                    }
                    break;
                case DRAGGING:
                    switch (event.getAction()) {
                        // Event: touch move
                        case MotionEvent.ACTION_MOVE:
                            // Context: none
                            // Side Effects:
                            model.resizeBox(iModel.selectedBox, normDX, normDY);
                            // Stay in the same state:
                            currentState = State.DRAGGING;
                            // Event: touch move
                            break;
                        case MotionEvent.ACTION_UP:
                            // Context: none
                            // Side Effects:
                            iModel.setSelected(null);
                            // Change state
                            currentState = State.READY;
                    }
                    break;
            }
        }
        return true;
    }
}
