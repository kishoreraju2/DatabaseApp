package com.example.kishore.database;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private TextView welcome;
    private TextView addr;
    private Button next;
    private static final String SELECT_SQL = "SELECT * FROM persons";
    private Cursor c;

    protected void OpenDatabase(){
        db = openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        OpenDatabase();
        welcome = (TextView) findViewById(R.id.textView);
        addr = (TextView) findViewById(R.id.textView2);
        next = (Button) findViewById(R.id.edNext);

        next.setOnClickListener(this);

        c=db.rawQuery(SELECT_SQL,null);
        c.moveToFirst();
        showRecords();

    }
    protected void showRecords(){
        String id = c.getString(0);
        String name = c.getString(1);
        String address = c.getString(2);
        welcome.setText("Hi "+name);
        addr.setText("Your address:"+address);

    }
    protected void showNext(){
        if(c.isLast() == false)
            c.moveToNext();


        showRecords();
    }
    public void onClick(View v){
        showNext();

    }
}
