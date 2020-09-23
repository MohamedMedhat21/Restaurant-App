package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView email, name, pass;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up");

        db = new DatabaseHelper(this);
        email = findViewById(R.id.emailText);
        name = findViewById(R.id.userNameText2);
        pass = findViewById(R.id.passwordText2);
        signUpButton = findViewById(R.id.signUpButton2);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString();
                String nameText = name.getText().toString();
                String passText = pass.getText().toString();
                if (emailText.equals("") || nameText.equals("") || passText.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the data", Toast.LENGTH_LONG).show();
                }
                else {
                    int flag = db.validateSignUp(emailText, nameText);
                    if (flag == 0) {
                        db.insert(emailText, nameText, passText);
                        Toast.makeText(getApplicationContext(), "Registered Successfully 100 ful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        if (flag == 1) {
                            Toast.makeText(getApplicationContext(), "email is taken", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "username is taken", Toast.LENGTH_LONG).show();
                        }
                    }
                }


            }
        });

    }
}