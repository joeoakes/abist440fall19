package edu.psu.ab.ist.sworks;
/*

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import edu.psu.ab.ist.sworks.blockchain.AES;
import edu.psu.ab.ist.sworks.blockchain.Block;
import edu.psu.ab.ist.sworks.mission.MissionStatus;
import edu.psu.ab.ist.sworks.pojo.AmbientLight;
import edu.psu.ab.ist.sworks.pojo.GPS;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    int view = R.layout.activity_main;
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 4;

    private TextView missionInfo;

    // TextView for the reply header
    private TextView mTextMessage;

    private TextView battery_prct;
    private TextView wifi_string;

    private SensorManager mSensorManager;

    private Sensor mSensorProximity;
    private Sensor mSensorLight;
    private Sensor mSensorPressure;


    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;
    private TextView mTextSensorPressure;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Initialize variable.
        battery_prct = findViewById(R.id.battery_prct);
        wifi_string = findViewById(R.id.wifi_name);

        try {
            int level = MissionStatus.getBatteryLevel(getApplicationContext(), 10);
            boolean wifi = MissionStatus.wifiConnected(getApplicationContext(), "ArchwoodAP");
            boolean mobile = MissionStatus.mobileConnected(getApplicationContext());
        } catch (Exception e) {

        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
        int percentage = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        battery_prct.setText("Battery Percentage is " + percentage + " %");


        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String ssid = info.getSSID();
        wifi_string.setText("Wi-Fi: " + ssid);


        mTextSensorLight = (TextView) findViewById(R.id.label_light);
        mTextSensorProximity = (TextView) findViewById(R.id.label_proximity);
        mTextSensorPressure = (TextView) findViewById(R.id.label_pressure);


        mSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);

        mSensorProximity = mSensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        String sensor_error = getResources().getString(R.string.error_no_sensor);

        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorPressure == null) {
            mTextSensorPressure.setText(sensor_error);
        }

        final String secretKey = "this is a password";

        missionInfo = findViewById(R.id.jsonPayloadMission);
        System.out.println("Trying to Mine block 1... ");

        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setLux(12345);

        Gson gson = new Gson();
        String jsonAmbientLight = gson.toJson(ambientLight);

        String encryptedAmbientLight = AES.encrypt(jsonAmbientLight, secretKey) ;

        addBlock(new Block(encryptedAmbientLight, "0"));

        missionInfo.setText(jsonAmbientLight);

        System.out.println("Trying to Mine block 2... ");
        GPS gps = new GPS();
        gps.setAltitude(12.50);
        gps.setBearing(25);
        gps.setLatitude(67.90);
        gps.setLongitude(90.098);
        gps.setSpeed(12);

        String jsonGps = gson.toJson(gps);

        String encryptedGps = AES.encrypt(jsonGps, secretKey) ;

        addBlock(new Block(encryptedGps, "0"));

        missionInfo.setText(jsonGps);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorPressure != null) {
            mSensorManager.registerListener(this, mSensorPressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int sensorType = sensorEvent.sensor.getType();

        float currentValue = sensorEvent.values[0];

        switch (sensorType) {
            // Event came from the light sensor.
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(getResources().getString(R.string.label_light, currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(getResources().getString(R.string.label_proximity, currentValue));
                break;
            case Sensor.TYPE_PRESSURE:
                mTextSensorPressure.setText(getResources().getString(R.string.label_pressure, currentValue));
                break;
            default:
        }
    }

    */
/**
 * Abstract method in SensorEventListener.  It must be implemented, but is
 * unused in this app.
 *//*

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }


    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}
*/