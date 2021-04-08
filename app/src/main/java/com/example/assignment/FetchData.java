package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class FetchData extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<dataclass> dataholderr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);

        recyclerView = findViewById(R.id.Fetchdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new datamanager(this).readalldata();
        dataholderr=new ArrayList<>();

        while(cursor.moveToNext()){
            dataclass obj = new dataclass(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            dataholderr.add(obj);
        }

        adapterclass adapter = new adapterclass(dataholderr);
        recyclerView.setAdapter(adapter);
    }
}