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
    private Button btnNote1;
    private Button btnNote2;
    private Button btnNote3;

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

        btnNote1 = findViewById(R.id.notes1);
        btnNote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, c1Notes.class));
            }
        });

        btnNote2 = findViewById(R.id.notes2);
        btnNote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, c2Notes.class));
            }
        });

        btnNote3 = findViewById(R.id.notes3);
        btnNote3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, c3Notes.class));
            }
        });

    }
}

