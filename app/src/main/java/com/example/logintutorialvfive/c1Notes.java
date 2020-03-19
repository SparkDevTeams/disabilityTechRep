package com.example.logintutorialvfive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class c1Notes extends AppCompatActivity {

    private Button mButtonToMain;
    private Button mButtonToTimerC1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1_notes);

        mButtonToMain = findViewById(R.id.mainButton1); //connects t xml
        mButtonToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c1Notes.this, SecondActivity.class));
            }
        });

        mButtonToTimerC1 = findViewById(R.id.btnToC1Timer); //connects t xml
        mButtonToTimerC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c1Notes.this, aTimer.class));
            }
        });

    }
}
