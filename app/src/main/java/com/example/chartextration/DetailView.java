package com.example.chartextration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DetailView extends View {

    public Bitmap imgview;
    public DetailView(Context context) {
        super(context);
//        setBackgroundColor(Color.CYAN);
    }

    public DetailView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DetailView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DetailView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas){

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        imgview = MainActivity.getBitmap();

        if(imgview!=null){
            Bitmap newMap = ChartView.getResizedBitmap(imgview, this.getWidth(), this.getHeight());
            canvas.drawBitmap(newMap,0,0,paint);
            Paint LinePaint = new Paint();
            LinePaint.setColor(Color.BLACK);
            LinePaint.setStrokeWidth(5);
            canvas.drawLine(280, 0, 280, 550, LinePaint);
            canvas.drawLine(0, 255, 560, 255, LinePaint);
        }else canvas.drawColor(Color.CYAN);


    }
}
