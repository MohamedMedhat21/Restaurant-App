package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    TextView mTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent i = getIntent();
        int timeLeft = i.getIntExtra("timer", 0) * 1000 * 60;
        mTextField = findViewById(R.id.textView);
        new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("Time remaining: " + (millisUntilFinished / 1000) / 60 + " : " + (millisUntilFinished / 1000) % 60);
            }
            public void onFinish() {
                mTextField.setText("Done!");
            }
        }.start();
    }
}