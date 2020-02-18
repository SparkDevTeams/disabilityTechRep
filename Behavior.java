package com.example.b3;

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


//Behavior class that has the frangment of the counter
public class Behavior extends Fragment implements View.OnClickListener
{


    public Behavior() {
        // Required empty public constructor
    }

    View v;
    TextView bhv;
    Button btn;
    Button bck;

    int counter=0;





//Counter, backtrack counter and name behavior button.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_behavior, container, false);

        btn = v.findViewById(R.id.count);
        bck = v.findViewById(R.id.x);

        btn.setText(String.valueOf(counter));
        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                btn.setText(String.valueOf(counter));
                counter++;
            }
        });

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                btn.setText(String.valueOf(counter));
            }
        });
        // Inflate the layout for this fragment
        return v;
    }




    @Override
    public void onClick(View v) {

    }


}
