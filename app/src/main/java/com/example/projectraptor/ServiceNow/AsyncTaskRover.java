package com.example.projectraptor.ServiceNow;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.projectraptor.Blockchain.AES;
import com.example.projectraptor.Blockchain.Block;
import com.example.projectraptor.Blockchain.StringUtil;
import com.example.projectraptor.MainActivity;
import com.example.projectraptor.SQLite.Dashboard;
import com.example.projectraptor.SQLite.DatabaseHelper;
import com.example.projectraptor.SQLite.ObjectInfo;
import com.google.gson.Gson;
import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.io.DataOutputStream;
import java.util.ArrayList;

public class AsyncTaskRover extends AsyncTask<String, Void, String> {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 4;
    final String secretKey = "this is a password";
    private final String USER_AGENT = "Mozilla/5.0";
    String user = "acm5772@psu.edu";
    String pwd = "440Team2";
    MainActivity mainActivity;
    private WeakReference<Context> mContext;
    String urlString = "https://emplkasperpsu2.service-now.com/api/now/table/u_team2_raptor";


    public AsyncTaskRover(Context context){
        mContext = new WeakReference<>(context);
    }
    @Override
    protected String doInBackground(String... params) {
        try
        {
            URL url = new URL(urlString);
            Context context = mContext.get();
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
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
            System.out.println(response.toString());

            HttpURLConnection con2 = (HttpURLConnection) url.openConnection();

            con2.setRequestMethod("POST");
            con2.setRequestProperty("User-Agent", USER_AGENT);
            con2.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con2.setRequestProperty("Content-Type","application/json");

            Gson gson = new Gson();
            Cursor c = databaseHelper.getAllData();
            while (c.moveToNext()) {
                ObjectInfo objectInfo = new ObjectInfo();
                objectInfo.setId(c.getString(0));
                objectInfo.setTimestamp(c.getString(1));
                objectInfo.setObjectFound(c.getString(2));
                objectInfo.setBatteryLevel(c.getString(3));
                objectInfo.setAmbientLight(c.getString(4));
                objectInfo.setHumidity(c.getString(5));
                objectInfo.setPressure(c.getString(6));
                objectInfo.setTemperatureString(c.getString(7));
                String jsonObject = gson.toJson(objectInfo);
                String encryptedObject = AES.encrypt(jsonObject, secretKey);
                addBlock(new Block(encryptedObject, "0"));
            }

            String blockchainJson = StringUtil.getJson(blockchain);
            System.out.println("\nThe block chain: ");
            System.out.println(blockchainJson);
            for(int i = 0; i < blockchain.size(); i++){
                String decryptedObject = AES.decrypt(blockchain.get(i).getData(), secretKey);
                System.out.println("\nDecrypted block data for block #" + (i+1) + ": " + decryptedObject);
                con2.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con2.getOutputStream());
                wr.writeBytes(decryptedObject);
                wr.flush();
                wr.close();
                responseCode = con2.getResponseCode();
                System.out.println("nSending 'POST' request to URL : " + url);
                System.out.println("Post Data : " + decryptedObject);
                System.out.println("Response Code : " + responseCode);

            }

            //String postJsonData = "{\"u_mission_id\":\"\",\"u_object_found\":\"\",\"u_gps_x\":\"\",\"u_gps_y\":\"\",\"u_battery_level\":\"\",\"u_pressure\":\"\",\"u_humidity\":\"\",\"u_ambient_light\":\"\",\"u_temperature\":\"\",\"u_timestamp\":\"\"}";
            // Send post request Just add values^^^^{\"u_mission_id\":\"\",\"u_object_found\":\"\",\"u_gps_x\":\"\",\"u_gps_y\":\"\",\"u_battery_level\":\"\",\"u_pressure\":\"\",\"u_humidity\":\"\",\"u_ambient_light\":\"\",\"u_temperature\":\"\",\"u_timestamp\":\"\"}
            //con2.setDoOutput(true);
            //DataOutputStream wr = new DataOutputStream(con2.getOutputStream());
            //wr.writeBytes(postJsonData);
            //wr.flush();
            //wr.close();

            //responseCode = con2.getResponseCode();
            //System.out.println("nSending 'POST' request to URL : " + url);
            //System.out.println("Post Data : " + decryptedObject);
            //System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(new InputStreamReader(con2.getInputStream()));
            response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            con2.disconnect();

            //printing result from response
            System.out.println(response.toString());

        } catch (Exception e) {
            Log.e("Main", e.getMessage(), e);
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

    //method for checking the integriy of the blockchain
    public static Boolean isChainValid() {
        //initialization of local variables for the method
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through the entire blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            //set the local variables to reference the current block in the chain and the previous block in the chain
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //calculate the hash of the current block again and compare it to the current block's hash that was calculated before. If the hash changed in the time
            // since the block was initially created, the entire chain is invalid and return false
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //calculate the hash of the previous block again and compare it to the previous block's hash that was calculated before. If the hash changed in the time
            // since the block was initially created, the entire chain is invalid and return false
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //If the current block hasn't been processed through the mining method, assume the chain is invalid and return false.
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }

        }
        //If everything looks ok, return true
        return true;
    }

    //method for adding a new block into the blockchain, accepts a Block object as a parameter
    public static void addBlock(Block newBlock) {
        //make the computer do work by mining the passed block before adding the block to the blockchain arraylist.
        // Pass in the difficulty of mining the block that was defined above.
        //This makes it harder or easier for the computer to mine the block.
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}

