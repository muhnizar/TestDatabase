package com.example.moohn.testdatabase;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

public class TestDatabaseActivity extends ListActivity {

    private CommentsDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view
        setContentView(R.layout.main);

        //initialise DAO
        datasource = new CommentsDataSource(this);
        datasource.open();

        //get all comments
        List<Comment> comments = datasource.getAllComments();

        //use simpleCursorAdapter to put in listview
        ArrayAdapter<Comment> commentArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comments);
        setListAdapter(commentArrayAdapter);

    }


    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
