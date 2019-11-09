package edu.psu.ab.ist.sworks;

import android.content.Intent;
import android.os.CountDownTimer;
/*import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
/*import org.tensorflow.contrib.android.TensorFlowInferenceInterface;*/

public class MissionTimer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_data);


    }
    //private static final long START_TIME_IN_MILLIS = 600000;

    /*private CountDownTimer mCountDownTimer;
    private TextView mTextViewCountDown;
    private boolean mTimerRunning;

    private long mTimeLeftInMillis;*/

    /*private static final String MODEL_FILE = "file:///android_asset/optimized_tfdroid.pb";
    private static final String INPUT_NODE = "I";
    private static final String OUTPUT_NODE = "O";

    private static final int[] INPUT_SIZE = {1,3};

    private TensorFlowInferenceInterface inferenceInterface;

    static {
        System.loadLibrary("tensorflow_inference");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_timer);

        *//*mTextViewCountDown = findViewById(R.id.text_view_countdown);
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        long startTimerFloat = extras.getInt(MainActivity.selectedMissionTimer);
        mTimeLeftInMillis = startTimerFloat;
        startTimer();
        updateCountDownText();
*//*

        inferenceInterface = new TensorFlowInferenceInterface();
        inferenceInterface.initializeTensorFlow(getAssets(), MODEL_FILE);


        final Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final EditText editNum1 = (EditText) findViewById(R.id.editNum1);
                final EditText editNum2 = (EditText) findViewById(R.id.editNum2);
                final EditText editNum3 = (EditText) findViewById(R.id.editNum3);

                float num1 = Float.parseFloat(editNum1.getText().toString());
                float num2 = Float.parseFloat(editNum2.getText().toString());
                float num3 = Float.parseFloat(editNum3.getText().toString());

                float[] inputFloats = {num1, num2, num3};

                inferenceInterface.fillNodeFloat(INPUT_NODE, INPUT_SIZE, inputFloats);

                inferenceInterface.runInference(new String[] {OUTPUT_NODE});

                float[] resu = {0, 0};
                inferenceInterface.readNodeFloat(OUTPUT_NODE, resu);

                final TextView textViewR = (TextView) findViewById(R.id.txtViewResult);
                textViewR.setText(Float.toString(resu[0]) + ", " + Float.toString(resu[1]));
            }
        });

    }*/
    }

    /*private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;

            }
        }.start();

        mTimerRunning = true;

    }*/

    /*private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }*/

    /*@Override
    public void onBackPressed () {
        //Back button is disabled
    }*/
/*}*/
