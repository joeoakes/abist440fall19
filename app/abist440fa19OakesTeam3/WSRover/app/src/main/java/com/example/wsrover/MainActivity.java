package com.example.wsrover;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    EditText emailText;
    TextView responseView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseView = (TextView)findViewById(R.id.responseView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Button btnGetData = (Button) findViewById(R.id.btnGetData);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTaskRover().execute();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new AsyncTaskRover().execute();

        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */
    }

    class AsyncTaskRover extends AsyncTask<String, Void, String> {

        private final String USER_AGENT = "Mozilla/5.0";
        String user = "tpl5148@psu.edu";
        String pwd = "Psuist3m$$";
        String urlString = "https://emplkasperpsu2.service-now.com/api/now/table/x_snc_team3_raptor_missiondata?sysparm_limit=1";


        @Override
        protected String doInBackground(String... params) {

            try
            {
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);

                Authenticator.setDefault(new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pwd.toCharArray());
                    }
                });

                int responseCode = con.getResponseCode();
                System.out.println("Sending get request : "+ url);
                System.out.println("Response code : "+ responseCode);

                // Reading response from input Stream
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String output;
                StringBuffer response = new StringBuffer();

                while ((output = in.readLine()) != null) {
                    response.append(output);
                }
                in.close();
                con.disconnect();

                //printing result from response
                System.out.println("Printing Data: " +response.toString());


            HttpURLConnection con2 = (HttpURLConnection) url.openConnection();

            con2.setRequestMethod("POST");
            con2.setRequestProperty("User-Agent", USER_AGENT);
            con2.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con2.setRequestProperty("Content-Type","application/json");
            //String postJsonData = "{\"mission_id\":\"0\",\"team_id\":\"3\",\"timestamp\":\"2014-10-10 04:05:50\",\"x_coordinate\":\"51\",\"y_coordinates\":\"1011\",\"battery_level\":\".58\",\"humidity\":\"20.00\",\"ambient_light\":\"20.21\",\"pressure\":\"52.25\",\"temperature\":\"254.25\",\"target_found\":\"true\",\"connection_strength\":\"true\"}";
            String postJsonData = "{\"mission_id\":\"0\",\"team_id\":\"3\",\"timestamp\":\"2014-10-10 04:05:50\",\"x_coordinate\":\"51\",\"y_coordinates\":\"1011\",\"battery_level\":" + getBattery() + ",\"humidity\":\"20.00\",\"ambient_light\":\"20.21\",\"pressure\":\"52.25\",\"temperature\":\"254.25\",\"target_found\":\"true\",\"connection_strength\":\"true\"}";
            // Send post request
            con2.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con2.getOutputStream());
            wr.writeBytes(postJsonData);
            wr.flush();
            wr.close();

            responseCode = con2.getResponseCode();
            System.out.println("nSending 'POST' request to URL : " + url);
            System.out.println("Post Data : " + postJsonData);
            System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(new InputStreamReader(con2.getInputStream()));
            response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            con2.disconnect();

            //printing result from response
            System.out.println(response.toString());


                return response.toString();

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        public int getBattery(){
            return 39;

        }
        protected void onPreExecute() {
            responseView.setText("");
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);

            responseView.setText(response);
        }



        @Override
        protected void onProgressUpdate(Void... values) {
            //If you want to update a progress bar ..do it here
        }
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

}
