package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    private TextView mTextField,timelefttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent i = getIntent();
        int timeLeft = i.getIntExtra("timer", 0) * 1000 * 60;
        mTextField = findViewById(R.id.textView);
        timelefttext = findViewById(R.id.timeLeft);
        new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("Time remaining: ");
                timelefttext.setText((millisUntilFinished / 1000) / 60 + " : " + (millisUntilFinished / 1000) % 60);
            }

            public void onFinish() {
                mTextField.setText("Your Order is Ready!");
            }
        }.start();
    }
}