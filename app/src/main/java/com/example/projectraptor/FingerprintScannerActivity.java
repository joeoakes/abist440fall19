package com.example.projectraptor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FingerprintScannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_scanner);

        final Executor executor = Executors.newSingleThreadExecutor();

        final BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("Fingerprint Authentication")
//                .setSubtitle("Subtitle")
                .setDescription("Unlock application using fingerprint scanner.")
                .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).build();

        Button authenticate = findViewById(R.id.authenticate);

        final FingerprintScannerActivity activity = this;

        authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {

                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);

                        finish();

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FingerprintScannerActivity.this, "Authenticated", Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                });


            }

        });

    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, FingerprintScannerActivity.class));
        finish();
    }

}
