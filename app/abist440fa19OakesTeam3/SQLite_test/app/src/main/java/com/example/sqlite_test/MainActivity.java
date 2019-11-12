package com.example.sqlite_test;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

//    SQLite sqLite = new SQLite();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println(SQLiteDB(10.123456780, 20.34, 30.12345));
    }

    public String SQLiteDB(double input1, double input2, double input3) {
        SQLiteDatabase db;
        db = openOrCreateDatabase("Team3Raptor", MODE_PRIVATE, null);
        try{
            db.execSQL("Drop Table If Exists MyTable");
            Log.d("Info", "Table Deleted");

            db.execSQL("Create Table If Not Exists MyTable(Column_1 INT(10), Column_2 INT(10), Column_3 INT(10));" );
            Log.d("Info", "Table Created");

            db.execSQL("INSERT INTO MyTable VALUES ("+ input1 + "," + input2 + "," + input3 + ");");

            Log.d("Info", "Data inserted");
        } catch (Exception e){
            Log.e("Error", e.toString());
        }

        Cursor C = db.rawQuery("Select * From MyTable", null);
        C.moveToFirst();

//        System.out.println(C.getString(C.getColumnIndex("Column_1")));
//        System.out.println(C.getString(C.getColumnIndex("Column_2")));
//        System.out.println(C.getString(C.getColumnIndex("Column_3")));

        Log.d("Column 1 data", C.getString(C.getColumnIndex("Column_1")));

        return C.getString(C.getColumnIndex("Column_1"));
    }

}
