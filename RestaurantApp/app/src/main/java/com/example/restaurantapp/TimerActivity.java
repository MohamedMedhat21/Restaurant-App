package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        final TextView mTextField = findViewById(R.id.textView);
        long timeLeft = 300000;
        new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("Time remaining: " + (millisUntilFinished / 1000) / 60 + " : " + (millisUntilFinished / 1000) % 60);
            }
            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();
    }
}