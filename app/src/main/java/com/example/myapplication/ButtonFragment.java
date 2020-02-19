package com.example.myapplication;

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


//ButtonFragment class that has the fragment of the counter
public class ButtonFragment extends Fragment implements View.OnClickListener {
    public ButtonFragment() {
        // Required empty public constructor
    }

    View v;
    TextView bhv;
    Button btn;
    Button bck;
    int counter = 0;

    //Counter, backtrack counter and name behavior button.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_behavior, container, false);

        btn = v.findViewById(R.id.count);
        bck = v.findViewById(R.id.x);

        btn.setText(String.valueOf(counter));
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn.setText(String.valueOf(counter));
                counter++;
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
}