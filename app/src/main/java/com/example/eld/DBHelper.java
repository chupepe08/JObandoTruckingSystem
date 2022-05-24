package com.example.eld;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "jobandodatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table UserLogin(username TEXT primary key, password TEXT, usertype TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists UserLogin");
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM UserLogin where username = ?", new String[]{username});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db. rawQuery("SELECT * FROM UserLogin where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM UserLogin", null);

        return cursor;
    }
}
