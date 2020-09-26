package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private TextView email, name, pass;
    private Button signUpButton;
    private ImageView backimge;

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
        backimge = findViewById(R.id.backimage);

        backimge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString();
                String nameText = name.getText().toString();
                String passText = pass.getText().toString();
                if (emailText.equals("") || nameText.equals("") || passText.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the data", Toast.LENGTH_LONG).show();
                } else {
                    int flag = db.validation(emailText, nameText, passText);
                    if (flag == 0) {
                        db.insert(emailText, nameText, passText);
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        if (flag == 1) {
                            Toast.makeText(getApplicationContext(), "email is taken", Toast.LENGTH_LONG).show();
                        }
                        if (flag == 2) {
                            Toast.makeText(getApplicationContext(), "username is taken", Toast.LENGTH_LONG).show();
                        }
                        if (flag == 3) {
                            Toast.makeText(getApplicationContext(), "email must end with @gmail.com", Toast.LENGTH_LONG).show();
                        }
                        if (flag == 4) {
                            Toast.makeText(getApplicationContext(), "password must be greater than 4 characters", Toast.LENGTH_LONG).show();
                        }
                    }
                }


            }
        });

    }
}