package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;

public class MenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[], s3[];
    int images[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.menuItems);
        s2 = getResources().getStringArray(R.array.Description);
        s3 = getResources().getStringArray(R.array.Prices);
        TextView total = (TextView)findViewById(R.id.totalNumbTxt);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3,images, total);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}