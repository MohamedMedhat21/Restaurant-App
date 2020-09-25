package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    DatabaseHelperMenu db;
    RecyclerView recyclerView;
    TextView total;
    Button checkoutButton;
    MyAdapter myAdapter;
    String menuItemsStr[], itemsDescriptionStr[], itemsPricesStr[], itemsTimeStr[];
    int images[] = {R.drawable.burger, R.drawable.pizza, R.drawable.juice, R.drawable.sandwich, R.drawable.pasta};
//    ArrayList<byte[]> imagesFromDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Menu");
        setContentView(R.layout.activity_menu);

        db = new DatabaseHelperMenu(this);
        recyclerView = findViewById(R.id.recyclerView);
        menuItemsStr = getResources().getStringArray(R.array.menuItems);
        itemsDescriptionStr = getResources().getStringArray(R.array.Description);
        itemsPricesStr = getResources().getStringArray(R.array.Prices);
        itemsTimeStr = getResources().getStringArray(R.array.Time);
        total = (TextView)findViewById(R.id.totalNumbTxt);
        checkoutButton = findViewById(R.id.checkoutBtn);

        myAdapter = new MyAdapter(this, menuItemsStr, itemsDescriptionStr, itemsPricesStr, itemsTimeStr, images, total);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = myAdapter.getMaxTime();
                if (time == -1) {
                    Toast.makeText(getApplicationContext(), "No items was selected", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent i = new Intent(MenuActivity.this, TimerActivity.class);
                    i.putExtra("timer", time);
                    startActivity(i);
                }
            }
        });
    }


//    private void getImages() {
//        Cursor cursor = db.getData();
//        while(!cursor.isAfterLast()) {
//            imagesFromDatabase.add(cursor.getBlob(3));
//        }
//    }
//    private Bitmap FromByteToBitmap(byte[] byteImg) {
//        Bitmap bitmap = BitmapFactory.decodeByteArray(byteImg, 0, byteImg.length);
//        return bitmap;
////        ImageView img = (ImageView)findViewById(R.id.img1);
////        img.setImageBitmap(bitmap);
//    }

    //just for once then delete it
//    private void insertImagesIntoDatabase() {
//        for (int i = 0; i < images.length; i++) {
//
//            Drawable drawable = VectorDrawableCompat.create(getResources(), images[i], getApplicationContext().getTheme());
//            Bitmap img = ((BitmapDrawable) drawable).getBitmap();
//            // or
////            Bitmap img = BitmapFactory.decodeResource(getApplicationContext().getResources(), images[i]);
//
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            img.compress(Bitmap.CompressFormat.PNG, 0, outputStream); //quality
//
//
//            byte[] byteImg = outputStream.toByteArray();
//            String description = "ya rb t4t8l", title = "g";
//            int price = 520, time = 5;
//            db.insert(title, description, price, time, byteImg);
//        }
//    }
}