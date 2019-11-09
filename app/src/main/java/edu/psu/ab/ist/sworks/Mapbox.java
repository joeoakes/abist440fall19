package edu.psu.ab.ist.sworks;

import android.content.Intent;
import android.os.Bundle;
/*import android.support.v7.app.ActionBar;*/
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

/*import android.support.v7.app.AppCompatActivity;*/

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Mapbox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapbox);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return true;
    }
    public void changeMap2D(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutid);
        linearLayout.setBackgroundResource(R.drawable.map2d);
    }

    public void changeMapEagle(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutid);
        linearLayout.setBackgroundResource(R.drawable.eaglemap);
    }

    public void changeMapPorperties(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutid);
        linearLayout.setBackgroundResource(R.drawable.propertiesmap);
    }
}
