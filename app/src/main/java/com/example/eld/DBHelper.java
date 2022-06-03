package com.example.eld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public static final String adminUname = "lol";
    public static final String adminPass = "adminHWbaliw";
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(UID TEXT primary key AUTOINCREMENT, username TEXT, password TEXT, UserType TEXT)");
        MyDB.execSQL("CREATE TABLE drivers(EmployeeID int primary key, FirstName TEXT, MiddleName TEXT, LastName TEXT, Age int, DrivingExperience int, TruckModel TEXT, TruckPlateNumber int)");
        MyDB.execSQL("CREATE TABLE currentJob(EmployeeID int primary key, Client TEXT, JobDescription TEXT, JobLocation TEXT, JobDate DATE)");
        MyDB.execSQL("CREATE TABLE completedJob(EmployeeID int primary key, Client TEXT, JobDescription TEXT, JobLocation TEXT, JobDate DATE, JobStatus TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE if exists users");
        MyDB.execSQL("DROP TABLE if exists drivers");
        MyDB.execSQL("DROP TABLE if exists currentJobs");
        MyDB.execSQL("DROP TABLE if exists completedJobs");
    }

    public Boolean insertUserData(String username, String password, String userType){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("userType", userType);
        long result = MyDB.insert( "users", null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return  true;
        }
    }

    public void createAdmin(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", adminUname);
        contentValues.put("password", adminPass);
        contentValues.put("UserType", "Employer");
        long result = MyDB.insert("users", null, contentValues);
    }

    public Boolean insertDriverData(int EmployeeID, String FirstName, String MiddleName, String LastName, int Age, int DrivingExperience, String TruckModel, String TruckPlateNumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EmployeeID", EmployeeID);
        contentValues.put("FirstName", FirstName);
        contentValues.put("MiddleName", MiddleName);
        contentValues.put("LastName", LastName);
        contentValues.put("Age", Age);
        contentValues.put("DrivingExperience", DrivingExperience);
        contentValues.put("TruckModel", TruckModel);
        contentValues.put("TruckPlateNumber", TruckPlateNumber);
        long result = MyDB.insert( "drivers", null, contentValues);

        if (result == -1){
            return false;
        }
        else{
            return  true;
        }

    }

    public Boolean insertCurrentJob(int EmployeeID, String Client, String JobDescription, String JobLocation, String Date){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EmployeeID", EmployeeID);
        contentValues.put("Client", Client);
        contentValues.put("JobDescription", JobDescription);
        contentValues.put("JobLocation", JobLocation);
        contentValues.put("Date", Date);
        long result = MyDB.insert("currentJob", null, contentValues);

        if (result == -1){
            return false;
        }
        else{
            return  true;
        }
    }

    public Boolean insertCompletedJob(int EmployeeID, String Client, String JobDescription, String JobLocation, String Date, String Status){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EmployeeID", EmployeeID);
        contentValues.put("Client", Client);
        contentValues.put("JobDescription", JobDescription);
        contentValues.put("JobLocation", JobLocation);
        contentValues.put("Date", Date);
        long result = MyDB.insert("currentJob", null, contentValues);

        if (result == -1){
            return false;
        }
        else{
            return  true;
        }
    }

    

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users where username = ?", new String[]{username});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkUserType(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users where username = ?", new String[]{username});
        cursor.moveToFirst();
        String type = cursor.getString(2);

        if(type.equals("Employer")){
            return true;
        }
        else if(type.equals("Employee")){
            return false;
        }
        return null;

    }
    
    public void displayCurrentJob(int employeeID, TextView TClient, TextView TJobDescription, TextView TJobLocation, TextView TJobDate){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM currentJob where EmployeeID = ?", new String[]{String.valueOf(employeeID)});
        while(cursor.moveToNext()){
            TClient.append(cursor.getString(1));
            TJobDescription.append(cursor.getString(2));
            TJobLocation.append(cursor.getString(3));
            TJobDate.append(cursor.getString(4));
        }
    }

    public void sendCurrentToCompletedJob(int employeeID){

        String eID, tc, tjb, tjl, tjd;
        ContentValues contentValues = new ContentValues();

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM currentJob where EmployeeID = ?", new String[]{String.valueOf(employeeID)});
        if(cursor.getCount() > 0){
            eID = cursor.getString(0);
            tc = cursor.getString(1);
            tjb = cursor.getString(2);
            tjl = cursor.getString(3);
            tjd = cursor.getString(4);

            contentValues.put("EmployeeID", eID);
            contentValues.put("Client", tc);
            contentValues.put("JobDescription", tjb);
            contentValues.put("JobLocation", tjl);
            contentValues.put("JobDate", tjd);
            MyDB.insert("completedJob", null, contentValues);

            MyDB.rawQuery("DELETE FROM currentJob where EmployeeID = ?", new String[]{String.valueOf(employeeID)});

        }
    }
    public void displayPastRecords(int employeeID, TextView TClient, TextView TJobDescription, TextView TJobLocation, TextView TJobDate, TextView TJobStatus){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM completedJob where EmployeeID = ?", new String[]{String.valueOf(employeeID)});
        while(cursor.moveToNext()){
            TClient.append(cursor.getString(1));
            TJobDescription.append(cursor.getString(2));
            TJobLocation.append(cursor.getString(3));
            TJobDate.append(cursor.getString(4));
            TJobStatus.append(cursor.getString(5));
        }
    }
}