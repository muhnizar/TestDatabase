package com.example.moohn.testdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";
    public static final String DATABASE_NAME = "comments.db";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE = "create table " + TABLE_COMMENTS + " ( " + COLUMN_ID +" integer primary key autoincrement, " +
            COLUMN_COMMENT + " text not null); ";

    public SQLLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLLiteHelper.class.getName(), "Upgrading database from version" +oldVersion +" to version "+newVersion);
        db.execSQL("DROP TABLE IF EXIST " +TABLE_COMMENTS);
        onCreate(db);
    }
}
