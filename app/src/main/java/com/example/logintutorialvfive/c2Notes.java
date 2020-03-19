package com.example.logintutorialvfive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class c2Notes extends AppCompatActivity {

    private Button mButtonToMain;
    private Button mButtonToTimerC2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c2_notes);

        mButtonToMain = findViewById(R.id.mainButton2); //connects t xml
        mButtonToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c2Notes.this, SecondActivity.class));
            }
        });

        mButtonToTimerC2 = findViewById(R.id.btnToC2Timer); //connects t xml
        mButtonToTimerC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c2Notes.this, bTimer.class));
            }
        });

    }
}
