package com.example.chartextration;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.view.View;


public class ChartView extends View implements BoxListener, CircleListner{
    public Bitmap imgview;
    Paint paint;
    BoxModel model;
    CircleModel Cmodel;
    BoxController controller;
    CircleController Ccontroller;
    InteractionModel iModel;
    CircleInteractionModel CiModel;
    float left, top, right, bottom;

    public ChartView(Context context) {
        super(context);
        paint = new Paint();
        setBackgroundColor(Color.BLUE);
    }

    public void setModel(BoxModel sm) { model = sm; }
    public void setCModel(CircleModel cm) { Cmodel = cm;}

    public void setIModel(InteractionModel im) {
        iModel = im;
    }
    public void setCiModel(CircleInteractionModel Cim) {CiModel = Cim;}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        iModel.setViewSize(this.getWidth(), this.getHeight());
        CiModel.setViewSize(this.getWidth(), this.getHeight());
    }

    public void setController(BoxController newC) {
        controller = newC;
        this.setOnTouchListener(controller);
    }

    public void setCircleContorller(CircleController newC){
        Ccontroller = newC;
        this.setOnTouchListener(Ccontroller);
    }


    @Override
    public void onDraw(Canvas canvas){

        imgview = MainActivity.getBitmap();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        if(imgview!=null){
            Bitmap newMap = getResizedBitmap(imgview, this.getWidth(), this.getHeight());
            canvas.drawBitmap(newMap,0,0,paint);
            if(model.boxes.size() < 1){
                model.addBox(0.14f,0.13f,0.3f,0.2f);
            }
            for (Box b : model.boxes) {
                if (b == iModel.selectedBox) {
                    paint.setColor(Color.MAGENTA);
                } else {
                    paint.setColor(Color.GREEN);
                }
                left = b.left * getWidth();
                top = b.top * getHeight();
                right = (b.left + b.width) * getWidth();
                bottom = (b.top + b.height) * getHeight();
                //System.out.println("box: " + b.left + "," + b.top + "," + b.width + "," + b.height);
                //System.out.println(left + "," + top + "," + right + "," + bottom);
                canvas.drawRect(left, top, right, bottom, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(left,top,30, paint);
                canvas.drawCircle(right,top,30, paint);
                canvas.drawCircle(left,bottom,30, paint);
                canvas.drawCircle(right,bottom,30, paint);
            }
            if (Cmodel.circles.size() > 0) {
                for (Circle c : Cmodel.circles) {
                    paint.setColor(Color.GREEN);
                    left = c.x * getWidth();
                    top = c.y * getHeight();
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.BLACK);
                    canvas.drawCircle(left, top, 10, paint);
                }
            }

        }else  canvas.drawColor(Color.BLUE);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
//        bm.recycle();
        return resizedBitmap;
    }
    public void modelChanged() {
        this.invalidate();
    }
}