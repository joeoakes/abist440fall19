package com.example.projectraptor;
// Testing git push Andrew
import android.content.Intent;
import android.os.Bundle;

import com.example.projectraptor.Blockchain.Block;
import com.example.projectraptor.SQLite.Dashboard;
import com.example.projectraptor.SQLite.DatabaseHelper;
import com.example.projectraptor.ServiceNow.AsyncTaskRover;
import com.example.projectraptor.Tensorflow.DetectorActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 4;
    final String secretKey = "this is a password";
    private boolean isAuthenticated = false;
    public DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        while (!isAuthenticated) {
            Intent fingerprintScannerIntent = new Intent(this, FingerprintScannerActivity.class);
            startActivity(fingerprintScannerIntent);
            isAuthenticated = true;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startMission(View view) {
        Intent intent = new Intent(this, DetectorActivity.class);
        startActivity(intent);
    }

    public void displayDashboard(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

    public void displayStats(View view) {
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
    }

    public void displayMap(View view) {
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }

    public void serviceNowUpload(View view) {
        AsyncTaskRover asyncTaskRover = (AsyncTaskRover) new AsyncTaskRover(getApplicationContext());
        asyncTaskRover.execute();
    }



    }

