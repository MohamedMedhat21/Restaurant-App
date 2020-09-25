package com.example.restaurantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "accounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(email text primary key, name text not null, password text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }

    public void insert(String email, String name, String password) {
        ContentValues obj = new ContentValues();
        obj.put("email", email);
        obj.put("name", name);
        obj.put("password", password);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("user", null, obj);
        db.close();
    }

    public int validation(String email, String name,String pass) {
        // 0 valid, 1 email exist, 2 userName exist
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email = ?", new String[]{email});
        Cursor cursor2 = db.rawQuery("select * from user where name = ?", new String[]{name});
        int cnt_email = cursor.getCount();
        int cnt_name = cursor2.getCount();
        cursor.close();
        cursor2.close();
        db.close();
        if (cnt_email > 0)
            return 1;
        if (cnt_name > 0)
            return 2;
        if (!email.endsWith("@gmail.com"))
            return 3;
        if(pass.length()<5)
            return 4;
        return 0;
    }

    public boolean validation(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where name = ? and password = ?", new String[]{name, password});
        int cnt = cursor.getCount();
        cursor.close();
        db.close();
        return cnt > 0;
    }

}
