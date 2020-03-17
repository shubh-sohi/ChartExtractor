package com.example.chartextration;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AxisView extends LinearLayout {
//    RelativeLayout currentLayout = (RelativeLayout) findViewById(R.id.AxisView);
    public AxisView(Context context) {
        super(context);
        this.setBackgroundColor(Color.LTGRAY);

//        final LayoutParams lparams = new LayoutParams(10,10); // Width , height

        LinearLayout axisRoot = new LinearLayout(context);
        axisRoot.setOrientation(LinearLayout.VERTICAL);
        axisRoot.setGravity(Gravity.CENTER);
        axisRoot.setMinimumWidth(550);
        axisRoot.setMinimumHeight(500);

        LinearLayout y_layout = new LinearLayout(context);
        y_layout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout x_layout = new LinearLayout(context);
        x_layout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout text_layout = new LinearLayout(context);
        text_layout.setMinimumHeight(150);
        text_layout.setBackgroundColor(Color.GRAY);
        text_layout.setGravity(Gravity.CENTER);

        TextView x_toText = new TextView(context);
        x_toText.setText(" to ");
        x_toText.setTextSize(15);

        TextView y_toText = new TextView(context);
        y_toText.setText(" to ");

        y_toText.setTextSize(15);

        TextView y_axis = new TextView(context);
        y_axis.setText("  Y Axis: ");
        y_axis.setTextSize(15);
        EditText enter_y = new EditText(context);
        enter_y.setText("0");
        EditText enter_y_to = new EditText(context);
        enter_y_to.setText("100");

        TextView x_axis = new TextView(context);
        x_axis.setText("  X Axis: ");
        x_axis.setTextSize(15);
        EditText enter_x = new EditText(context);
        enter_x.setText("0");
        EditText enter_x_to = new EditText(context);
        enter_x_to.setText("100");

        TextView locations = new TextView(context);
        locations.setText("0,0");
        locations.setTextSize(20);

        x_layout.setPadding(0,0,0,150);
        y_layout.addView(y_axis);
        y_layout.addView(enter_y);
        y_layout.addView(x_toText);
        y_layout.addView(enter_y_to);

        x_layout.addView(x_axis);
        x_layout.addView(enter_x);
        x_layout.addView(y_toText);
        x_layout.addView(enter_x_to);

        text_layout.addView(locations);


        axisRoot.addView(y_layout);
        axisRoot.addView(x_layout);
        axisRoot.addView(text_layout);

        this.addView(axisRoot);


    }

    public AxisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AxisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AxisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas){
    }
}
