/*
Project: PSA Grover Vehicle Sworks edition
Feature: Main Android Activity
Course: IST 440w Section 1 Fall 2019
Author: Joe Oakes
Date Developed: 4/8/2019
Last Date Changed:
Rev: development build 1
*/
package edu.psu.ab.ist.sworks;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
/*import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;*/
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLOutput;

import edu.psu.ab.ist.sworks.exceptions.MissionStatusException;
import edu.psu.ab.ist.sworks.mission.MissionStatus;
import edu.psu.ab.ist.sworks.tensor.CameraActivity;
import edu.psu.ab.ist.sworks.tensor.DetectorActivity;


public class MainActivity extends AppCompatActivity {
    //int view = R.layout.activity_main;


    // TextView for the reply header
    private TextView mTextMessage;

    private Spinner setTimer;

    private Spinner setMissionType;

    public static final String selectedMissionTimer = "timer";

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variable.
        TextView batteryLvl = findViewById(R.id.batteryLevel);
        TextView wifiText = findViewById(R.id.wifiConnectivity);
        TextView bluetoothText = findViewById(R.id.bluetoothConnectivity);

        setTimer = (Spinner) findViewById(R.id.missionTimerDDB);
        setMissionType = (Spinner) findViewById(R.id.missionType);

        final Button startMissionbtn = (Button) findViewById(R.id.startMissionbtn);
        final Button missionDatabtn = (Button) findViewById(R.id.missionDatabtn);
        final Button mapBoxbtn = (Button) findViewById(R.id.mapBoxbtn);


        startMissionbtn.setEnabled(false);
        //missionDatabtn.setEnabled(false);
        //mapBoxbtn.setEnabled(false);

        try {
            int level = MissionStatus.getBatteryLevel(getApplicationContext(), 10);
            batteryLvl.append(level + " %");

            //Add back when testing to PSU
            //boolean wifi = MissionStatus.wifiConnected(getApplicationContext(), "PSU");

            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(mWifi.isConnected())
                wifiText.append("ON");
            else
                wifiText.append("OFF");

            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                // Device does not support Bluetooth
                bluetoothText.append("NOT SUPPORTED");
            } else if (!mBluetoothAdapter.isEnabled()) {
                // Bluetooth is not enabled :)
                bluetoothText.append("OFF");
            } else {
                // Bluetooth is enabled
                bluetoothText.append("ON");
            }


        } catch (Exception e) {
            batteryLvl.setText("Battery Level Too Low");
            Log.d("Battery Level Too Low", e.getMessage());

        }

        ////mission type
        ArrayAdapter<String> missionTypeChoices = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.missionTypes)){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        missionTypeChoices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setMissionType.setAdapter(missionTypeChoices);


        ///mission type ends

        ArrayAdapter<String> timerChoices = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.times)){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        timerChoices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setTimer.setAdapter(timerChoices);

        setTimer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                    startMissionbtn.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);

        int percentage = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        battery_prct.setText("Battery Percentage is " + percentage + " %");*/
    }

    public void openMapbox(View view) {
        Intent intent = new Intent(this, Mapbox.class);
        startActivity(intent);
    }

    public void openHelp(View view) {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void startMissionTimer(View view) {
        /*Intent intent = new Intent(this, MissionTimer.class);
        String text = setTimer.getSelectedItem().toString();
        switch(text) {
            case "5 Minutes":
                intent.putExtra(selectedMissionTimer, 300000);
                startActivityForResult(intent, 1);
                break;
            case "10 Minutes":
                intent.putExtra(selectedMissionTimer, 600000);
                startActivityForResult(intent, 1);
                break;
            case "15 Minutes":
                intent.putExtra(selectedMissionTimer, 900000);
                startActivityForResult(intent, 1);
                break;
            case "20 Minutes":
                intent.putExtra(selectedMissionTimer, 1200000);
                startActivityForResult(intent, 1);
                break;
            case "25 Minutes":
                intent.putExtra(selectedMissionTimer, 1500000);
                startActivityForResult(intent, 1);
                break;
            case "30 Minutes":
                intent.putExtra(selectedMissionTimer, 1800000);
                startActivityForResult(intent, 1);
                break;
        }*/

        Intent intent = new Intent(this, DetectorActivity.class);
        startActivity(intent);

    }

    public void openMissionData(View view) {
        Intent intent = new Intent(this, MissionData.class);
        startActivity(intent);

    }
}