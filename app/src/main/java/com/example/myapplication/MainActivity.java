package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int colCounter = 0;
    int rowCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Timer stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TableLayout buttonLayout = (TableLayout) findViewById(R.id.buttonLayout);


        Button addFragButton = findViewById(R.id.addFragmentButton);
        addFragButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TableRow row = new TableRow(getApplicationContext());
                row.setId(rowCounter);
                ButtonFragment buttonFrag = new ButtonFragment();
                fragmentTransaction.add(rowCounter,buttonFrag);
                fragmentTransaction.commit();
                buttonLayout.addView(row);
                if(colCounter == 3){
                    rowCounter++;
                    colCounter = 0;
                } else {
                    colCounter++;
                }
            }
        });
        //End Fragment stuff
    }


}
