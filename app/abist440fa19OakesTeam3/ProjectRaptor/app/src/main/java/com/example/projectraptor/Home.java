package com.example.projectraptor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectraptor.tensorflow.lite.examples.detection.DetectorActivity;

public class Home extends AppCompatActivity  {

    private Button startButton;
    private Button stopButton;
    private Button mapsButton;
    private Button dataButton;
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startButton =  findViewById(R.id.start_button);
        stopButton =  findViewById(R.id.stop_button);
        mapsButton =  findViewById(R.id.maps_button);
        dataButton =  findViewById(R.id.data_button);
        testButton =  findViewById(R.id.test_button);





    }

    public void start(View view){
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();



    }
    public void stop(View view){

        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();



    }










    public void openMaps(View view) {
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);

    }

    public void openData(View view) {
        Intent myIntent = new Intent(this, Data.class);
        startActivity(myIntent);
    }



    public void openTest(View view) {
        Intent myIntent = new Intent(this, SensorActivity.class);
        startActivity(myIntent);
    }

    public void openCamera(View view) {
        Intent myIntent = new Intent(this, DetectorActivity.class);
        startActivity(myIntent);
    }
}
