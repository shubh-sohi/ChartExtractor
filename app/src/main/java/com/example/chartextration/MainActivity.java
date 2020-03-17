package com.example.chartextration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private static Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout root = findViewById(R.id.root);
        root.setOrientation(LinearLayout.VERTICAL);
        LinearLayout topOrient = new LinearLayout(this);
        topOrient.setOrientation(LinearLayout.HORIZONTAL);

        // MVC
        BoxModel model = new BoxModel();
        BoxController controller = new BoxController1();
        InteractionModel iModel = new InteractionModel();

        CircleModel Cmodel = new CircleModel();
        CircleController Ccontroller = new CircleController1();
        CircleInteractionModel CiModel = new CircleInteractionModel();

        DetailView detailview = new DetailView(this);
        ChartView chartView = new ChartView(this);
        AxisView axisview = new AxisView(this);


        topOrient.addView(detailview, new LinearLayout.LayoutParams(540,500));
        topOrient.addView(axisview, new LinearLayout.LayoutParams(540,500));
        root.addView(topOrient);
        root.addView(chartView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        root.addView(detailview, new LinearLayout.LayoutParams(500,500));
//        root.addView(chartView, new LinearLayout.LayoutParams(500,500));
//        root.addView(chartView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));

        chartView.setModel(model);
        chartView.setController(controller);
        chartView.setIModel(iModel);
        controller.setModel(model);
        controller.setIModel(iModel);
        model.addSubscriber(chartView);
        iModel.addSubscriber(chartView);

        chartView.setCModel(Cmodel);
        chartView.setCircleContorller(Ccontroller);
        chartView.setCiModel(CiModel);
        Ccontroller.setModel(Cmodel);
        Ccontroller.setCIModel(CiModel);
        Cmodel.addSubscriber(chartView);
        CiModel.addSubscriber(chartView);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        // return true so that the main_menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.firstItem:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a chart"), 123);
                return true;

            case R.id.secondItem:
                return true;

            case R.id.thirdItem:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData(); //The uri with the location of the image
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

            } catch (IOException e) {
                e.printStackTrace();
            }


            // do something with the selected image
        }
    }
    public static Bitmap getBitmap(){
        return bitmap;
    }
}
