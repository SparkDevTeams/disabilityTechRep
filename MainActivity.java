package com.example.b3;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;





public class MainActivity extends AppCompatActivity
{

    Button firstBtn = null;
    LinearLayout myLayout = null;
    int counter [] = new int [2];
    int i =0;

    int b =0;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add Behavior buttom
        firstBtn =  findViewById(R.id.firstBtn);


        //When add behavior button is clicked a fragment pops up
        firstBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


            FragmentManager fragmentManager= getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Behavior behavior = new Behavior ();
            fragmentTransaction.add(R.id.fragment_container,behavior);
            fragmentTransaction.commit();




            }
                });


            }





}