package com.example.projectraptor.POJO;

import android.content.ContentValues;

import com.example.projectraptor.SQL.DatabaseSetup;
import com.example.projectraptor.SQL.ISqlInsertHandler;

public class AmbientLight implements ISqlInsertHandler {
    private float _lux;

    public AmbientLight() {}

    public AmbientLight(float lux) {
        this._lux = lux;
    }

    public float getLux() {
        return _lux;
    }

    public void setLux(float lux) {
        this._lux = lux;
    }

    @Override
    public ContentValues columnValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseSetup.AMBI_LUX, this._lux);
        values.put(DatabaseSetup.TIME_STAMP, String.valueOf(new java.util.Date()));
        return values;
    }

    @Override
    public String tableName() {
        return DatabaseSetup.TABLE_AMBI;
    }
}
