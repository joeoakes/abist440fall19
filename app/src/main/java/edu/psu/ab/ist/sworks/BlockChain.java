package edu.psu.ab.ist.sworks;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

import edu.psu.ab.ist.sworks.blockchain.Block;
import edu.psu.ab.ist.sworks.blockchain.StringUtil;


public class BlockChain extends AppCompatActivity {
    Gson gson = new Gson();
    TextView holder;
    ArrayList<String> payload = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_chain);
        holder = (TextView) findViewById(R.id.spaceHolder);
        payload.add("Andy");
        payload.add("Liu");
        payload.add("22");
        addBlock(new Block(payload.toString(), "0"));
        holder.setText(StringUtil.getJson(blockchain));
    }
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 2;
    public static void addBlock(Block newBlock) {
        //make the computer do work by mining the passed block before adding the block to the blockchain arraylist.
        // Pass in the difficulty of mining the block that was defined above.
        //This makes it harder or easier for the computer to mine the block.
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}

