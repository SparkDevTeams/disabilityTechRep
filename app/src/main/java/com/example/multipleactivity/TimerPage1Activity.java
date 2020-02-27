package com.example.multipleactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimerPage1Activity extends AppCompatActivity {

    private Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_page1);

        mainButton = findViewById(R.id.btnBackToMain); //connects t xml
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimerPage1Activity.this, MainActivity.class));
            }
        });

    }

}
