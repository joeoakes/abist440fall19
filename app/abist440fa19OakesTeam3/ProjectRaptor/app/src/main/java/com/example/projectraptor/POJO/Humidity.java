package com.example.projectraptor.POJO;

import android.content.ContentValues;

import com.example.projectraptor.SQL.DatabaseSetup;
import com.example.projectraptor.SQL.ISqlInsertHandler;

public class Humidity implements ISqlInsertHandler {
    private float _relativeHumidity;

    public Humidity() {}

    public Humidity(float relHumidity) {
        this._relativeHumidity = relHumidity;
    }

    public float getRelHumidity() {
        return _relativeHumidity;
    }

    public void setRelHumidity(float relHumidity) {
        this._relativeHumidity = relHumidity;
    }

    @Override
    public ContentValues columnValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseSetup.HUMIDITY_REL, this._relativeHumidity);
        values.put(DatabaseSetup.TIME_STAMP, String.valueOf(new java.util.Date()));
        return values;
    }

    @Override
    public String tableName() {
        return DatabaseSetup.TABLE_HUMIDITY;
    }
}
