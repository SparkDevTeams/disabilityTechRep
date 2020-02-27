package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Button trialA;
    private Button trialB;
    private Button trialC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trialA = findViewById(R.id.trial1);
        trialA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, aTimer.class));
            }
        });
        trialB = findViewById(R.id.trial2);
        trialB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, bTimer.class));
            }
        });
        trialC = findViewById(R.id.trial3);
        trialC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, cTimer.class));
            }
        });
    }
}

