package com.example.moohn.testdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {


    private SQLLiteHelper dbHelper;
    private SQLiteDatabase database;

    public CommentsDataSource(Context context) {
      dbHelper = new SQLLiteHelper(context);
    }

    public void open(){
       database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Comment createComment(String comment){
        ContentValues contentValues = new ContentValues();
       // contentValues.put();

        return null;
    }


}
