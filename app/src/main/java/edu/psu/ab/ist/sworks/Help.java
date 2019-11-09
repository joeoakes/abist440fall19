package edu.psu.ab.ist.sworks;

/*import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }
    /*public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return true;
    }*/
}
