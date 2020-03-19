package com.example.logintutorialvfive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import android.view.View;
import android.widget.*;
import android.content.*;
import android.view.*;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout;

    private Button trialA;
    private Button trialB;
    private Button trialC;
    private Button btnNote1;
    private Button btnNote2;
    private Button btnNote3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });


        trialA = findViewById(R.id.trial1);
        trialA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // added this v
                //Intent intent = new Intent(SecondActivity.this, aTimer.class);
                //intent.putExtra("User_Name", getIntent().getStringExtra("User_Name"));
                //startActivity(intent);

                startActivity(new Intent(SecondActivity.this, aTimer.class));
            }
        });
        trialB = findViewById(R.id.trial2);
        trialB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, bTimer.class));
            }
        });
        trialC = findViewById(R.id.trial3);
        trialC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, cTimer.class));
            }
        });

        btnNote1 = findViewById(R.id.notes1);
        btnNote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, c1Notes.class));
            }
        });

        btnNote2 = findViewById(R.id.notes2);
        btnNote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, c2Notes.class));
            }
        });

        btnNote3 = findViewById(R.id.notes3);
        btnNote3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, c3Notes.class));
            }
        });


    }

    /**
     *
     */
    private void logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logoutMenu: {
                logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
