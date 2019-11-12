package com.example.wsrover;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.io.DataOutputStream;

public class AsyncTaskRover extends AsyncTask<String, Void, String> {

    private final String USER_AGENT = "Mozilla/5.0";
    String user = "sxh599@psu.edu";
    String pwd = "ProjectRaptorTeam3";
    String urlString = "https://emplkasperpsu2.service-now.com/api/now/table/x_snc_team3_raptor_missiondata";


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
            System.out.println("Printg Data: " +response.toString());

            HttpURLConnection con2 = (HttpURLConnection) url.openConnection();

            con2.setRequestMethod("POST");
            con2.setRequestProperty("User-Agent", USER_AGENT);
            con2.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con2.setRequestProperty("Content-Type","application/json");
            String postJsonData = "{\"u_mission_id\":\"\",\"u_object_found\":\"\",\"u_gps_x\":\"\",\"u_gps_y\":\"\",\"u_battery_level\":\"\",\"u_pressure\":\"\",\"u_humidity\":\"\",\"u_ambient_light\":\"\",\"u_temperature\":\"\",\"u_timestamp\":\"\"}}";
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


    @Override
    protected void onPostExecute(String result) {
        //update your ui here
    }

    @Override
    protected void onPreExecute() {
        //do any code before exec
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        //If you want to update a progress bar ..do it here
    }


}
