package com.example.projectraptor.BlockChain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.util.ArrayList;

public class BlockPass {
    public static String convert = "";
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;
    public static String [] results= {" ", " ", " ", "", " "} ;

    double alti = 20;// get value from mission capture
    double lati = 35;
    double longi = 75 ;



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

    @RequiresApi(api = Build.VERSION_CODES.O)

    public String blockprocess(int a, String [] args) {
        final String secretKey = "this is a password";
        //add our blocks to the blockchain ArrayList:


        System.out.println("Trying to Mine block 1... ");
        //call the addBlock method and pass a new block object with an AES encrypted POJO as the data for the block
        //the previous hash value passed into the new block object is zero because this is the genesis block
        Target target1 = new Target("Soccer ball", alti,lati,longi,"10/22/20"," ball","found");

        Gson gson = new Gson();
        String jsonTarget = gson.toJson(target1);


        String jsonTargetEncrypted = AES.encrypt(jsonTarget, secretKey);

        // need for loop to add each data to block
        // addBlock(new Block(jsonTargetEncrypted,blockchain.size().hash - i));

        for(int i = a; i < blockchain.size(); i++){

            /*Target target1 = new Target();
            target1.setObjetName("Soccer Ball");
            target1.setDate("09/25/2019");
            target1.setTime("11:37:01 pm");
            target1.setLocation("41°24'12.2\"N 2°10'26.5\"E");
            target1.setStatus("Found");
            Gson gson = new Gson();
            String jsonTarget = gson.toJson(target1);*/




            // addBlock(new Block(jsonTargetEncrypted,blockchain.size().hash - i));
        }

        addBlock(new Block(jsonTargetEncrypted,blockchain.get(blockchain.size()-1).hash));
        addBlock(new Block(jsonTargetEncrypted, "0"));


        // for


        //call the addBlock method and pass a new block object with an AES encrypted POJO as the data for the block
        //get the hash of the previous block in the arraylist and pass it into the block constructor



        //call the addBlock method and pass a new block object with an AES encrypted POJO as the data for the block
        //get the hash of the previous block in the arraylist and pass it into the block constructor



        //call the isChainValid() method to check the validity of the block hashes
        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = StringUtil.getJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);

        for(int i = 0; i < blockchain.size(); i++){
            System.out.println("\nDecrypted block data for block #" + (i+1) + ": " + AES.decrypt(blockchain.get(i).getData(), secretKey));
        }


        return secretKey;
    }
}
