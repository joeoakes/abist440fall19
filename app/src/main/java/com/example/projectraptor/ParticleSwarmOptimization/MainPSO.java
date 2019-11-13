package com.example.projectraptor.ParticleSwarmOptimization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projectraptor.ParticleSwarmOptimization.Particle.FunctionType;
import com.example.projectraptor.ParticleSwarmOptimization.Swarm;
import com.example.projectraptor.R;

public class MainPSO extends AppCompatActivity {

    /*
    private TextView inertiaChoice;
    private TextView cognitiveChoice;
    private TextView socialChoice;

    private TextView particleLabel;
    private TextView epoch_label;

    private TextView finalPosition_Label;
    private TextView finalResult_label;



    private TextView bestEvaluation;
*/

    Swarm swarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NOTE: The commented out code was the Android Code I had from before for this. It'll be commented out for now
/*
        inertiaChoice = findViewById(R.id.inertia);
        cognitiveChoice = findViewById (R.id.cognitiveComponent);
        socialChoice = findViewById (R.id.socialComponent);
        particleLabel =findViewById(R.id.particleComponent);
        epoch_label = findViewById(R.id.epochComponent);
        finalPosition_Label = findViewById(R.id.finalPosition);
        finalResult_label = findViewById(R.id.finalResult);

        //Set Up Defaults
        inertiaChoice.setText("Inertia: " +Double.toString(swarm.DEFAULT_INERTIA));
        cognitiveChoice.setText(" Cognitive Component: " + Double.toString(swarm.DEFAULT_COGNITIVE));
        socialChoice.setText( "Social Component: " + Double.toString(swarm.DEFAULT_SOCIAL));
*/
        System.out.println("Inertia: " + Double.toString(swarm.DEFAULT_INERTIA));

        System.out.println("Cognitive Component: " + Double.toString(swarm.DEFAULT_SOCIAL));
        System.out.println("Social Component: " + Double.toString(swarm.DEFAULT_SOCIAL));

        Particle.FunctionType function = getFunction(1);
        Integer particles = 3;
        Integer epochs = 500;

        /*
        particleLabel.setText("Particles: " + Integer.toString(particles));
        epoch_label.setText("Epochs: " + Integer.toString(epochs));
*/
        System.out.println("Particles: " + Integer.toString(particles));
        System.out.println("Epochs: " + Integer.toString(epochs));
        swarm = new Swarm(function, particles, epochs);
        swarm.run();
/*
        finalPosition_Label.setText("Best Position (X Axis): " + Double.toString(swarm.bestPosition.getX()));

        finalResult_label.setText("Best Result: " + Double.toString(swarm.bestEval));

        */

        System.out.println("Best Position (X Axis): " + Double.toString(swarm.bestPosition.getX()));
        System.out.println("Best Result: " + Double.toString(swarm.bestEval));

    }

    private static void printMenu() {
        System.out.println("----------------------------MENU----------------------------");
        System.out.println("Select a function:");
        System.out.println("1. (x^4)-2(x^3)");
        System.out.println("2. Ackley's Function");
        System.out.println("3. Booth's Function");
        System.out.println("4. Three Hump Camel Function");
        System.out.print("Function:  ");
    }

    public static FunctionType getFunction(int input) {
        if (input == 1) {
            return FunctionType.FunctionA;
        } else if (input == 2) {
            return FunctionType.Ackleys;
        } else if (input == 3) {
            return FunctionType.Booths;
        } else if (input == 4) {
            return FunctionType.ThreeHumpCamel;
        } else {
            System.out.println("Invalid Input.");
            return null;
        }
    }
}



