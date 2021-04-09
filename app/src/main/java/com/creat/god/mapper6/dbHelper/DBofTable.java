package com.creat.god.mapper6.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBofTable extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Bd";
    public static final String TABLE_CONTACTS = "contact";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_POWER = "power";


    public DBofTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID + " integer primary key,"
                + KEY_NAME + " text,"
                + KEY_POWER + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(db);
    }

}
