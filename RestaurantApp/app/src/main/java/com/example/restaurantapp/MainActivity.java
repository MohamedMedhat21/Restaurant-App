package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private TextView name, pass;
    private Button loginButton, signUpButton;
    private ImageView backimge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign In");

        db = new DatabaseHelper(this);
        name = findViewById(R.id.userNameText);
        pass = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);
        backimge=findViewById(R.id.backimage);

        backimge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                String passText = pass.getText().toString();
                if (nameText.equals("") || passText.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all the data", Toast.LENGTH_LONG).show();
                } else {
                    if (db.validation(nameText, passText)) {
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}