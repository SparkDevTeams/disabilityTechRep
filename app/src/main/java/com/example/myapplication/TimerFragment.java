package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    public long mStartTimeInMillis;
    private long mTimeLeftInMillis = mStartTimeInMillis;

    public TimerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

                String input = mEditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(getActivity(), "Field can't be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;

                if (millisInput == 0) {
                    Toast.makeText(getActivity(), "Please enter a positive number  ", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(millisInput);
                mEditTextInput.setText("");

            }
        });
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
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
        mButtonSet.setVisibility(View.INVISIBLE);

    }

    private void startTimer() {
        mCountdownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                updateProgressBar();
            }


            @Override
            public void onFinish() {
                mTimerRunning = false;
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

    private void pauseTimer() {
        mCountdownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mButtonSet.setVisibility(View.VISIBLE);
        mEditTextInput.setVisibility(View.VISIBLE);
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        mProgressBar.setProgress(mProgressBar.getMax());

    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);

        mTextViewCountdown.setText(timeLeftFormatted);
    }

    private void updateProgressBar() {
        int progress = (int) (mTimeLeftInMillis * 100) / (int) mStartTimeInMillis;
        mProgressBar.setProgress(progress);
    }
}
