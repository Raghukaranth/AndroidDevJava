package com.example.androiddevjava.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AttendanceDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "attendance.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "attendance";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_REASON = "reason";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_IS_CHECKED = "is_checked";

    public AttendanceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_REASON + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_IS_CHECKED + " INTEGER);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For simplicity, drop and recreate table on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data method
    public long insertAttendance(String reason, String time, boolean isChecked) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_REASON, reason);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_IS_CHECKED, isChecked ? 1 : 0);

        return db.insert(TABLE_NAME, null, values);
    }
}

