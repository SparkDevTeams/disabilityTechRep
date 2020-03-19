package com.example.logintutorialvfive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class c3Notes extends AppCompatActivity {

    private Button mButtonToMain;
    private Button mButtonToTimerC3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c3_notes);

        mButtonToMain = findViewById(R.id.mainButton3); //connects t xml
        mButtonToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c3Notes.this, SecondActivity.class));
            }
        });

        mButtonToTimerC3 = findViewById(R.id.btnToC3Timer); //connects t xml
        mButtonToTimerC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c3Notes.this, cTimer.class));
            }
        });

    }
}
