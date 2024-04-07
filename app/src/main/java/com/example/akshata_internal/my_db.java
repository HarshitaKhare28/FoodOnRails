package com.example.akshata_internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class my_db extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserDB";
    private static final String TABLE_NAME = "user";
    private static final String COL_1 = "username";
    private static final String COL_2 = "email";
    private static final String COL_3 = "phone";
    private static final String COL_4 = "password";

    public my_db(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_1 + " TEXT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String username, String email, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, username);
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, phone);
        contentValues.put(COL_4, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public String getPasswordByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String password = null;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_4}, COL_1 + "=?", new String[]{username}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int passwordIndex = cursor.getColumnIndex(COL_4);
                if (passwordIndex != -1) {
                    password = cursor.getString(passwordIndex);
                }
            }
            cursor.close();
        }
        db.close();
        return password;
    }
}
