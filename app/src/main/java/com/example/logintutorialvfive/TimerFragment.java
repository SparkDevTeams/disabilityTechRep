package com.example.logintutorialvfive;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.logintutorialvfive.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*

 * alarmSound is being called when it's null, since it's being called
 * early. We should initialize alarm before set button gets pressed.
 * */

public class TimerFragment extends Fragment {
    View v;
    private EditText mEditTextInput;
    private TextView mTextViewCountdown;

    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private ProgressBar mProgressBar;
    private CountDownTimer mCountdownTimer;


    private boolean mTimerRunning;
    private boolean mChronometerRunning;
    public long mStartTimeInMillis;
    private long mTimeLeftInMillis = mStartTimeInMillis;
    private long totalTime;

    public MediaPlayer alarmSound; //alarm sound can't be initialized in this spot for the audio -Alonzo Jasmin 2/27/2019
    public Handler handler;
    public Runnable countUp;

    private int clientNumber;
    private String sessionTime;

    public TimerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //bug occured due to alarmSound not initializing earlier. Added hear to be the first step -Alonzo Jasmin 2/27/2019

        alarmSound = MediaPlayer.create(getActivity(), R.raw.acoustic_guitar_alarm_ringtone);

        //alarmSound = MediaPlayer.create(getActivity(), R.raw.acoustic_guitar_alarm_ringtone);


        v = inflater.inflate(R.layout.timer_fragment_layout, container, false);

        mEditTextInput = v.findViewById(R.id.edit_text_input);

        mTextViewCountdown = v.findViewById(R.id.text_view_countdown);

        mProgressBar = v.findViewById(R.id.progressbar);
        mProgressBar.setProgress(100);

        mButtonSet = v.findViewById(R.id.button_set);

        mButtonStartPause = v.findViewById(R.id.start_pause_button);
        mButtonReset = v.findViewById(R.id.reset_button);

        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String input = mEditTextInput.getText().toString();
                    if (input.length() == 0) {
                        Toast.makeText(getActivity(), "Field can't be empty.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String[] token = input.split(":");
                    if(token.length == 0){
                        token = new String[2];
                        token[0] = "0";
                        token[1] = "0";
                    } else if (token.length == 1) {
                        if(input.charAt(0) == ':'){
                            String secs = token[0];
                            token = new String[2];
                            token[0] = "0";
                            token[1] = secs;
                        } else {
                            String mins = token[0];
                            token = new String[2];
                            token[0] = mins;
                            token[1] = "0";
                        }
                    }
                    if(token[0].length() == 0) token[0] = "0";
                    long millisInput = Long.parseLong(token[0]) * 60000;
                    millisInput += Long.parseLong(token[1]) * 1000;

                    setTime(millisInput);

                    totalTime = millisInput;

                    mEditTextInput.setText("");
                } catch(Exception e) {
                    System.out.println("---------ERROR on Set: "+e+" ----------");
                }
            }
        });
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else if (mChronometerRunning) {
                    pauseChronometer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();
        return v;
    }

    private void setTime(long milliseconds) {
        mEditTextInput.setVisibility(View.VISIBLE);
        mButtonSet.setVisibility(View.VISIBLE);
        mStartTimeInMillis = milliseconds;
        resetTimer();

    }

    private void setDatabaseValue(String key, Object value) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        // added this v
        //String Username = getIntent().getStringExtra("User_Name");
        DatabaseReference myRef  = FirebaseDatabase.getInstance().getReference()
                .child(firebaseAuth.getUid()).child("Timed Sessions").child("Client " + clientNumber).child(sessionTime).child(key);
        myRef.setValue(value);
    }

    private void startTimer() {


        if(mTimeLeftInMillis == 0){
            startChronometer();
        } else {
            setDatabaseValue("Duration", formatTimeLeft(mTimeLeftInMillis));
            setDatabaseValue("Session finished", false); //Mark session as not finished
            mCountdownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                    updateProgressBar();
                }

                @Override
                public void onFinish() {
                    //Mark session as finished
                    setDatabaseValue("Session finished", true);
                    mTimerRunning = false;
                    //starts the audio once time reaches zero --Alonzo Jasmin 2/27/2020

                    alarmSound.start();
                    //keeps audio in loop
                    alarmSound.setLooping(true);
                    //System.out.println("on finish"); debugging purpose -Alonzo Jasmin 2/27/2020
                    mButtonStartPause.setText("Start");
                    mButtonStartPause.setVisibility(View.INVISIBLE);
                    mButtonReset.setVisibility(View.VISIBLE);
                }
            }.start();

            mTimerRunning = true;
            mButtonStartPause.setText("pause");
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mEditTextInput.setVisibility(View.INVISIBLE);
        }
    }

    private void pauseTimer() {
        mCountdownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void pauseChronometer() {
        mChronometerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
        handler.removeCallbacks(countUp);
    }

    private void startChronometer(){
        handler = new Handler();
        countUp = new Runnable() {
            public void run() {
                updateCountUpText();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(countUp, 1000);
        mChronometerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonSet.setVisibility(View.INVISIBLE);
        mEditTextInput.setVisibility(View.INVISIBLE);
    }

    private void resetTimer() {

        mButtonSet.setVisibility(View.VISIBLE);
        mEditTextInput.setVisibility(View.VISIBLE);
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        mProgressBar.setProgress(mProgressBar.getMax());
        //when reset is clicked it checks if audio is playing then stops the audio -Alonzo Jasmin 2/27/2020
        if(alarmSound.isPlaying()) {
            alarmSound.stop();
            //sets the mediaPlayer to end state -Alonzo Jasmin / Andre Marin 3/2/2020
            alarmSound.reset();
            //prevents media objects to fall back and fail altogether -Alonzo Jasmin 3/2/2020
            alarmSound.release();
            //sets alarm sound back to proper media and file location -Alonzo Jasmin 3/2/2020

            alarmSound = MediaPlayer.create(getActivity(), R.raw.acoustic_guitar_alarm_ringtone);

            //System.out.println("stops music");    used for debugging -Alonzo Jasmin 2/27/2020
        }
    }

    public static String formatTimeLeft(long milliseconds) {
        int minutes = (int) (milliseconds / 1000) / 60;
        int seconds = (int) (milliseconds / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        return timeLeftFormatted;
    }

    private void updateCountDownText() {
        String timeLeftFormatted = formatTimeLeft(mTimeLeftInMillis);
        mTextViewCountdown.setText(timeLeftFormatted);
    }

    private void updateCountUpText() {
        String minutes = ((String) mTextViewCountdown.getText()).substring(0,2);
        String seconds = ((String) mTextViewCountdown.getText()).substring(3);
        int minInt = Integer.parseInt(minutes);
        int secInt = Integer.parseInt(seconds);
        if(secInt == 59){
            minInt++;
            secInt = 0;
        } else {
            secInt++;
        }

        String timeLeftFormatted = String.format("%02d:%02d", minInt, secInt);

        mTextViewCountdown.setText(timeLeftFormatted);
    }

    private void updateProgressBar() {
        int progress = (int) (mTimeLeftInMillis * 100) / (int) mStartTimeInMillis;
        mProgressBar.setProgress(progress);
    }

    public void setClientNumber(int number) {
        this.clientNumber = number;
    }

    public void setSessionTime(String session) {
        this.sessionTime = session;
    }

    /*
    private long[] calculateRates(long millis){
        aTimer a = new aTimer();
        long[] rates = a.calculateRates(millis);
        Log.i("--------------RATES", " RATES GO HERE");
        return rates;
    }*/
}

