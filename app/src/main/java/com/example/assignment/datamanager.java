package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class   datamanager extends SQLiteOpenHelper {

    private static final String dbname = "dbdataentry";


    public datamanager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry="create table tbl_data ( id integer primary key autoincrement, name text, age text, city text, gender text)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String qry="DROP TABLE IF EXISTS tbl_data";
        db.execSQL(qry);
        onCreate(db);

    }

    //inserting data
    public String adddata(String name, String age, String city , String gender){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("age",age);
        cv.put("city",city);
        cv.put("gender",gender);
        float res = database.insert("tbl_data",null,cv);

        if(res==-1)
            return "Failed";
        else
            return  "Successfully inserted";

    }

    //fetching data
    public Cursor readalldata()
    {
        SQLiteDatabase database=this.getWritableDatabase();
        String qry="select * from tbl_data order by id desc";
        Cursor cursor=database.rawQuery(qry,null);
        return  cursor;
    }


}
