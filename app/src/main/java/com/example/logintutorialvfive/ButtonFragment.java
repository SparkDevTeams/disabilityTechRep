package com.example.logintutorialvfive;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

//import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.app.Fragment;
import android.widget.Toast;


import com.example.logintutorialvfive.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//ButtonFragment class that has the fragment of the counter
public class ButtonFragment extends Fragment implements View.OnClickListener {
    private int clientNumber;
    private String sessionTime;

    public ButtonFragment() {
        // Required empty public constructor
    }

    View v;
    TextView bhv;
    Button btn;
    Button bck;
    int counter = 0;
    private String name = "Blank name"; //leaving this here to make it easier to spot issues

    //Counter, backtrack counter and name behavior button.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_behavior, container, false);

        btn = v.findViewById(R.id.count);
        bck = v.findViewById(R.id.x);

        btn.setText(String.valueOf(counter));
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter++;
                btn.setText(String.valueOf(counter));
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                // added this v
                //String Username = getIntent().getStringExtra("User_Name");
                DatabaseReference myRef  = FirebaseDatabase.getInstance().getReference()
                        .child(firebaseAuth.getUid()).child("Timed Sessions").child("Client " + clientNumber).child(sessionTime).child(name);
                myRef.setValue(counter);
            }
        });

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter > 0){
                    counter--;
                } else {
                    Toast.makeText(getActivity(), "No, you can't have negative numbers.", Toast.LENGTH_SHORT).show();
                }
                btn.setText(String.valueOf(counter));
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    //I need this because otherwise, Java will complain.
    @Override
    public void onClick(View v) {
        //Just don't complain.
    }

    public int getCounter() {
        return counter;
    }

    public String getBehavior() {
        return bhv.getText().toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClientNumber(int number) {
        this.clientNumber = number;
    }

    public void setSessionTime(String session) {
        this.sessionTime = session;
    }

}