package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String menuItemsStr[], itemsDescriptionStr[], itemsPricesStr[];
    int images[] = {R.drawable.burger, R.drawable.pizza, R.drawable.juice, R.drawable.sandwich, R.drawable.pasta};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Menu");
        setContentView(R.layout.activity_menu);
        recyclerView = findViewById(R.id.recyclerView);
        menuItemsStr = getResources().getStringArray(R.array.menuItems);
        itemsDescriptionStr = getResources().getStringArray(R.array.Description);
        itemsPricesStr = getResources().getStringArray(R.array.Prices);
        TextView total = (TextView)findViewById(R.id.totalNumbTxt);

        MyAdapter myAdapter = new MyAdapter(this, menuItemsStr, itemsDescriptionStr, itemsPricesStr,images, total);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}