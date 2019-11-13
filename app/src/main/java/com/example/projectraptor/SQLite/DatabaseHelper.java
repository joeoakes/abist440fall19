package com.example.projectraptor.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.projectraptor.ServiceNow.AsyncTaskRover;


public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Team2Raptor.db";
    public static final String TABLE_NAME = "New440team2_tables";
    public static final String COL_1="U_MISSION_ID";
    public static final String COL_2="U_TIMESTAMP";
    public static final String COL_3="U_OBJECT_FOUND";
    public static final String COL_4="U_BATTERY_LEVEL";
    public static final String COL_5= "U_AMBIENT_LIGHT";
    public static final String COL_6="U_HUMIDITY";
    public static final String COL_7="U_PRESSURE";
    public static final String COL_8="U_TEMPERATURE_FARENHEIT";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table " + TABLE_NAME + " (MISSION_ID INTEGER PRIMARY KEY AUTOINCREMENT, TIMESTAMP TEXT, OBJECT_FOUND TEXT, BATTERY_LEVEL INTEGER, AMBIENT_LIGHT INTEGER,  HUMIDITY INTEGER,  PRESSURE INTEGER, TEMPERATURE_FARENHEIT INTEGER)");
        db.execSQL("create table " + TABLE_NAME + " (U_MISSION_ID INTEGER PRIMARY KEY AUTOINCREMENT, U_TIMESTAMP TEXT, U_OBJECT_FOUND TEXT, U_BATTERY_LEVEL INTEGER, U_AMBIENT_LIGHT INTEGER,  U_HUMIDITY INTEGER,  U_PRESSURE INTEGER, U_TEMPERATURE_FARENHEIT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String u_timestamp, String u_object_Found, String u_battery_Level, String u_ambient_light, String u_humidity, String u_pressure, String u_temperature) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, u_timestamp);
        contentValues.put(COL_3, u_object_Found);
        contentValues.put(COL_4, u_battery_Level);
        contentValues.put(COL_5, u_ambient_light);
        contentValues.put(COL_6, u_humidity);
        contentValues.put(COL_7, u_pressure);
        contentValues.put(COL_8, u_temperature);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String u_mission_id, String u_timestamp, String u_object_Found, String u_battery_Level, String u_ambient_light, String u_humidity, String u_pressure, String u_temperature) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, u_mission_id);
        contentValues.put(COL_2, u_timestamp);
        contentValues.put(COL_3, u_object_Found);
        contentValues.put(COL_4, u_battery_Level);
        contentValues.put(COL_5, u_ambient_light);
        contentValues.put(COL_6, u_humidity);
        contentValues.put(COL_7, u_pressure);
        contentValues.put(COL_8, u_temperature);
        db.update(TABLE_NAME, contentValues, "U_MISSION_ID = ?", new String[]{u_mission_id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "U_MISSION_ID = ?", new String[]{id});
    }
}
