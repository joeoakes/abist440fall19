package edu.psu.ab.ist.sworks;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;*/

import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.psu.ab.ist.sworks.database.DBHelper;
import edu.psu.ab.ist.sworks.database.DatabaseSetup;

public class MissionData extends AppCompatActivity {
    TextView firstName;
    TextView lastName;
    TextView age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_data);
        /* firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        age = (TextView) findViewById(R.id.age);
        ArrayList person;

        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS myTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + "myTable(LastName VARCHAR, FirstName VARCHAR, Age INT(3));");

        db.execSQL("INSERT INTO myTable VALUES ('mai', 'kevin',21);");


        lastName.setText("Mai");
        firstName.setText("Kevin");
        age.setText("22");
        Log.d("JOE", c.getString(c.getColumnIndex("FirstName"))); */



    }

    /**
     * Inserts temperature readings into TABLE_CLIMATE
     *
     * @param db            SQLite Database
     * @param temperature   String temperature
     * @param humidity      String humidity
     * @param pressure      String pressure
     */
    private static void updateClimateTable (SQLiteDatabase db, String temperature, String humidity, String pressure) {
        db.execSQL("INSERT INTO " + DatabaseSetup.TABLE_CLIMATE + "(" + ") VALUES ('"+ temperature + "');");
        db.execSQL("INSERT INTO " + DatabaseSetup.TABLE_CLIMATE + "(" + ") VALUES ('"+ humidity + "');");
        db.execSQL("INSERT INTO " + DatabaseSetup.TABLE_CLIMATE + "(" + ") VALUES ('"+ pressure + "');");
    }

    /**
     * Opens up a database in the path of the device
     *
     * @param path String path
     * @return db      
     */

    public SQLiteDatabase openDB(String path) {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(path, null, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return db;
    }


    public void openBlockChain(View view) {
        Intent intent = new Intent(this, BlockChain.class);
        startActivity(intent);
    }
/*
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + "myTable" + "( "
                + GPS_ID + " INTEGER PRIMARY KEY " + " )");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + "myTable" +"( " + GPS_ID + "GPS ID )" );
        Cursor c = sqLiteDatabase.rawQuery("Select * FROM myTable", null);
        c.moveToFirst();
        Log.d("David", c.getString(c.getColumnIndex("gpsID")));
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        // drops table if exists, and then calls onCreate which implements
        // our new schema
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(database);
    }*/
}