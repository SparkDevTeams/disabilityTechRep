package com.example.logintutorialvfive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.Date;

public class ThirdActivity extends AppCompatActivity {

    int colCounter = 0;
    int rowCounter = 0;
    int fragIdTracker = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Timer stuff
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_third);
        final TableLayout buttonLayout = (TableLayout) findViewById(R.id.buttonLayout);

        Button addFragButton = findViewById(R.id.addFragmentButton);
        View frag1 = findViewById(R.id.behavior1);

        View frag2 = findViewById(R.id.behavior2);
        frag2.setVisibility(View.INVISIBLE);

        View frag3 = findViewById(R.id.behavior3);
        frag3.setVisibility(View.INVISIBLE);

        View frag4 = findViewById(R.id.behavior4);
        frag4.setVisibility(View.INVISIBLE);

        View frag5 = findViewById(R.id.behavior5);
        frag5.setVisibility(View.INVISIBLE);

        View frag6 = findViewById(R.id.behavior6);
        frag6.setVisibility(View.INVISIBLE);

        View frag7 = findViewById(R.id.behavior7);
        frag7.setVisibility(View.INVISIBLE);

        View frag8 = findViewById(R.id.behavior8);
        frag8.setVisibility(View.INVISIBLE);

        View frag9 = findViewById(R.id.behavior9);
        frag9.setVisibility(View.INVISIBLE);

        final int[] fragIds = {R.id.behavior1,R.id.behavior2,R.id.behavior3,
                         R.id.behavior4,R.id.behavior5,R.id.behavior6,
                         R.id.behavior7,R.id.behavior8,R.id.behavior9};

        addFragButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(fragIdTracker > 8){
                    Toast.makeText(ThirdActivity.this, "Max behaviors reached!", Toast.LENGTH_SHORT).show();
                } else {
                    findViewById(fragIds[fragIdTracker]).setVisibility(View.VISIBLE);
                    if(fragIdTracker == 1){
                        findViewById(R.id.behavior1).findViewById(R.id.count).setBackgroundResource(R.drawable.btn_1);
                    }
                    else if(fragIdTracker ==2){
                        findViewById(R.id.behavior2).findViewById(R.id.count).setBackgroundResource(R.drawable.btn_2);
                    }
                    else if(fragIdTracker == 3){
                        findViewById(R.id.behavior3).findViewById(R.id.count).setBackgroundResource(R.drawable.btn_3);
                    }
                    else if(fragIdTracker ==4){
                        findViewById(R.id.behavior4).findViewById(R.id.count).setBackgroundResource(R.drawable.btn_4);
                    }
                    else if(fragIdTracker == 5){
                        findViewById(R.id.behavior5).findViewById(R.id.count).setBackgroundResource(R.drawable.btn_5);
                    }
                    else if(fragIdTracker ==6){
                        findViewById(R.id.behavior6).findViewById(R.id.count).setBackgroundResource(R.drawable.btn_6);
                    }
                    else if(fragIdTracker == 7){
                        findViewById(R.id.behavior7).findViewById(R.id.count).setBackgroundResource(R.drawable.btn_7);
                    }
                    else if(fragIdTracker ==8){
                        findViewById(R.id.behavior8).findViewById(R.id.count).setBackgroundResource(R.drawable.btn_8);
                    }


                    fragIdTracker++;
                }


                /*FragmentManager fragmentManager= getFragmentManager();
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
                }*/
            }
        });

        String sessionName = "Session at " + new Date().toString();
        Bundle b = getIntent().getExtras();
        int id = b.getInt("clientNumber");

        setupTimerFragment(sessionName, id);
        setupButtonFragments(sessionName, id);

        Button mainMenuButton = findViewById(R.id.mainMenuButton);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, SecondActivity.class));
            }
        });

        //End Fragment stuff
    }

    public void setupButtonFragments(String sessionID, int clientNumber) {
        FragmentManager manager = getFragmentManager();

        int[] fragmentIDs = {
                R.id.behavior1,
                R.id.behavior2,
                R.id.behavior3,
                R.id.behavior4,
                R.id.behavior5,
                R.id.behavior6,
                R.id.behavior7,
                R.id.behavior8,
                R.id.behavior9
        };

        for(int k = 0; k < fragmentIDs.length; k++) {
            ButtonFragment fragment = (ButtonFragment)manager.findFragmentById(fragmentIDs[k]);
            fragment.setName("behavior" + (k + 1));
            fragment.setSessionTime(sessionID);
            fragment.setClientNumber(clientNumber);

        }
    }

    public void setupTimerFragment(String sessionID, int clientNumber) {
        FragmentManager manager = getFragmentManager();
        TimerFragment timer = (TimerFragment)manager.findFragmentById(R.id.list);
        timer.setSessionTime(sessionID);
        timer.setClientNumber(clientNumber);
    }


}
