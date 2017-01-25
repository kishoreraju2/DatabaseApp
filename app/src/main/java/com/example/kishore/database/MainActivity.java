package com.example.kishore.database;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name;
    private EditText address;
    private Button add ;
    private Button view ;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.edName);
        address = (EditText) findViewById(R.id.edAddress);
        add = (Button) findViewById(R.id.edAdd);
        view = (Button) findViewById(R.id.edView);
        add.setOnClickListener(this);
        view.setOnClickListener(this);
        createDatabase();
    }

    protected void createDatabase(){
        db=openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS persons(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name VARCAHR,address VARCHAR);");
    }

    protected void insertIntoDB(){
        String Name = name.getText().toString();
        String Address = address.getText().toString();

        String query = "INSERT INTO persons (name,address) VALUES('"+Name+"','"+Address+"');";
        db.execSQL(query);

    }
    private void showPeople(){
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v){
        if(v == add){
            insertIntoDB();
        }
        else if(v == view){
            showPeople();
        }
    }

}
