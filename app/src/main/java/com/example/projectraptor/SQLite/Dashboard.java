package com.example.projectraptor.SQLite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectraptor.Blockchain.AES;
import com.example.projectraptor.Blockchain.Block;
import com.example.projectraptor.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class Dashboard extends AppCompatActivity {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 4;
    DatabaseHelper myDb;
    EditText editObjectFound, editAmbient, editHumidity, editPressure, editTemperature, editTextId;
    TextView editTimestamp;
    TextView editBattery;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    final String secretKey = "this is a password";
    Button btnviewUpdate;


    private BroadcastReceiver batteryLevelReciever = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            editBattery.setText(String.valueOf(level));
        }
        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        myDb = new DatabaseHelper(this);

        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        editTimestamp = (TextView) findViewById(R.id.editText_timestamp);
        editTimestamp.setText(date);

        editObjectFound = (EditText) findViewById(R.id.editText_ObjectFound);

        editBattery = (TextView) findViewById(R.id.editText_battery);
        this.registerReceiver(this.batteryLevelReciever, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        editAmbient = (EditText) findViewById(R.id.editText_ambient);

        editHumidity = (EditText) findViewById(R.id.editText_humidity);

        editPressure = (EditText) findViewById(R.id.editText_pressure);

        editTemperature= (EditText) findViewById(R.id.editText_temperature);

        editTextId = (EditText) findViewById(R.id.editText_id);

        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(Dashboard.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Dashboard.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editTimestamp.getText().toString(),
                                editObjectFound.getText().toString(),
                                editBattery.getText().toString(),
                                editAmbient.getText().toString(),
                                editHumidity.getText().toString(),
                                editPressure.getText().toString(),
                                editTemperature.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(Dashboard.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Dashboard.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gson gson = new Gson();
                        boolean isInserted = myDb.insertData(
                                editTimestamp.getText().toString(),
                                editObjectFound.getText().toString(),
                                editBattery.getText().toString(),
                                editAmbient.getText().toString(),
                                editHumidity.getText().toString(),
                                editPressure.getText().toString(),
                                editTemperature.getText().toString());
                        String addDataJSON = gson.toJson(isInserted);
                        String encryptedAddData = AES.encrypt(addDataJSON, secretKey);
                        addBlock(new Block(encryptedAddData, "0"));
                        if (isInserted == true)
                            Toast.makeText(Dashboard.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Dashboard.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor c = myDb.getAllData();
                        if (c.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (c.moveToNext()) {
                            buffer.append("ID: "+ c.getString(0)+ "\n");
                            buffer.append("Timestamp: "+ c.getString(1)+ "\n");
                            buffer.append("Object Found: "+ c.getString(2)+ "\n");
                            buffer.append("Battery Level: "+ c.getString(3)+ "\n");
                            buffer.append("Ambient Light: "+ c.getString(4)+ "\n");
                            buffer.append("Humidity: "+ c.getString(5)+ "\n");
                            buffer.append("Pressure: "+ c.getString(6)+ "\n");
                            buffer.append("Temperature: "+ c.getString(7)+ "\n");
                        }

                        // Show all data
                        showMessage("Team 2 SQLite server", buffer.toString());
                    }
                }
        );
    }



    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
