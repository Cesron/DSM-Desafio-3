package com.example.dsm_desafio_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table session(email text primary key)");
        db.execSQL("create table users(email text primary key, password text, role text)");
        db.execSQL("create table favorites(id integer primary key autoincrement, model text, vin text, chassis text, motor text, seats text, year text, price double, brand text, color text, email text, foreign key(email) references users(email))");
        db.execSQL("create table cars(id integer primary key autoincrement, model text, vin text, chassis text, motor text, seats text, year text, price double, brand text, color text)");
        db.execSQL("insert into users values('admin@gmail.com', '1234', 'Admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists cars");
    }

    public Boolean insertData(String email, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("role", role);
        long result = db.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean insertCar(String model, String vin, String chassis, String motor, String seats, String year, String price, String brand, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("model", model);
        contentValues.put("vin", vin);
        contentValues.put("chassis", chassis);
        contentValues.put("motor", motor);
        contentValues.put("seats", seats);
        contentValues.put("year", year);
        contentValues.put("price", price);
        contentValues.put("brand", brand);
        contentValues.put("color", color);
        long result = db.insert("cars", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllCars() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from cars", null);
        return res;
    }

    public Boolean insertFavoriteCar(String email, String model, String vin, String chassis, String motor, String seats, String year, String price, String brand, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("model", model);
        contentValues.put("vin", vin);
        contentValues.put("chassis", chassis);
        contentValues.put("motor", motor);
        contentValues.put("seats", seats);
        contentValues.put("year", year);
        contentValues.put("price", price);
        contentValues.put("brand", brand);
        contentValues.put("color", color);
        long result = db.insert("favorites", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getFavoriteCars(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from favorites where email = ?", new String[]{email});
        return res;
    }

    public Boolean updateCar(String id, String model, String vin, String chassis, String motor, String seats, String year, String price, String brand, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("model", model);
        contentValues.put("vin", vin);
        contentValues.put("chassis", chassis);
        contentValues.put("motor", motor);
        contentValues.put("seats", seats);
        contentValues.put("year", year);
        contentValues.put("price", price);
        contentValues.put("brand", brand);
        contentValues.put("color", color);
        long result = db.update("cars", contentValues, "id = ?", new String[]{id});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean deleteCar(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("cars", "id = ?", new String[]{id});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void setSession(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        db.delete("session", null, null);
        db.insert("session", null, contentValues);
    }

    public String getSession() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select email from session", null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }

    public String getRole(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select role from users where email = ?", new String[]{email});
        cursor.moveToFirst();

        return cursor.getString(0);
    }

}
