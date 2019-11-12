package com.example.projectraptor.POJO;

import android.content.ContentValues;

import com.example.projectraptor.SQL.DatabaseSetup;
import com.example.projectraptor.SQL.ISqlInsertHandler;



public class Temp implements ISqlInsertHandler {
    private float _temperature;

    public Temp() {}

    public Temp(float temperature) {
        this._temperature = temperature;
    }

    public float getTemperature() {
        return _temperature;
    }

    public void setTemperature(float temperature) {
        this._temperature = temperature;
    }

    @Override
    public ContentValues columnValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseSetup.TEMPERATURE_VALUE, this._temperature);
        values.put(DatabaseSetup.TIME_STAMP, String.valueOf(new java.util.Date()));
        return values;
    }

    @Override
    public String tableName() {
        return DatabaseSetup.TABLE_TEMPERATURE;
    }
}
