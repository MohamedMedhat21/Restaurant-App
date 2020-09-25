package com.example.restaurantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperMenu extends SQLiteOpenHelper {
    public DatabaseHelperMenu(Context context) {
        super(context, "menuItems.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table menu(title text not null, description text not null, price integer not null, time integer not null, img image not null)");
        //id can be deleted as there is (rowed) which is builtin in SQLite
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists menu");
        onCreate(db);
    }

    public void insert(String title, String description, int price, int time, byte[] img) {
        ContentValues obj = new ContentValues();
        obj.put("title", title);
        obj.put("description", description);
        obj.put("price", price);
        obj.put("time", time);
        obj.put("img", img);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("menu", null, obj);
        db.close();
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from menu", null);
        db.close();
        cursor.moveToFirst();
        return cursor;
    }

}
