package com.example.moohn.testdatabase;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

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

    public void onClick(View view){
        ArrayAdapter<Comment> commentArrayAdapter  = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment =  null;

        switch (view.getId()){
            case R.id.add :
                String[] comments = new String[]{"apple" , "android", "blackbery"};
                int nextInt = new Random().nextInt(3);
                // save into database
                comment = datasource.createComment(comments[nextInt]);
                //mapping to layput
                commentArrayAdapter.add(comment);
                break;
            case R.id.delete:
                if(getListAdapter().getCount()>0){
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    //mapping to layput
                    commentArrayAdapter.remove(comment);
                }
                break;
        }
    commentArrayAdapter.notifyDataSetChanged();
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
