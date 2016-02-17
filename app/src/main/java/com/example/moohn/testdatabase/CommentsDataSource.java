package com.example.moohn.testdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CommentsDataSource {

    private SQLLiteHelper dbHelper;
    private SQLiteDatabase database;
    private String [] allColumn = {SQLLiteHelper.COLUMN_ID, SQLLiteHelper.COLUMN_COMMENT};


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
        contentValues.put(SQLLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(SQLLiteHelper.TABLE_COMMENTS, null, contentValues );
        Cursor cursor =  database.query(SQLLiteHelper.TABLE_COMMENTS,allColumn, SQLLiteHelper.COLUMN_ID +"=" +insertId,null,null,null,null);
        cursor.moveToFirst();
        return cursorToComment(cursor);
    }

    private void deleteComment(Comment comment){
        long id = comment.getId();
        database.delete(SQLLiteHelper.TABLE_COMMENTS, SQLLiteHelper.COLUMN_ID + "=" + id, null);
    }

    private List<Comment> getAllComments(){
        List<Comment> comments = new ArrayList<>();
        Cursor cursor = database.query(SQLLiteHelper.TABLE_COMMENTS, allColumn, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            comments.add(cursorToComment(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    private Comment cursorToComment(Cursor cursor){
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}