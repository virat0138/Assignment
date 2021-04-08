package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DataEntry extends AppCompatActivity {
    TextInputLayout name,city,age,gender;
    FloatingActionButton view;
    Button submit, Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        name =  findViewById(R.id.Name);
        city = findViewById(R.id.City);
        gender =  findViewById(R.id.Gender);
        age =  findViewById(R.id.Age);
        view =  findViewById(R.id.View);
        submit =  findViewById(R.id.Submit);
        Exit = findViewById(R.id.exit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insert();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataEntry.this, FetchData.class);
                startActivity(i);
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    public void onBackPressed() {
        //disabled back button
    }


    private void insert(){
        String Name = name.getEditText().getText().toString();
        String City = city.getEditText().getText().toString();
        String  Age = age.getEditText().getText().toString();
        String Gender = gender.getEditText().getText().toString();

        String res = new datamanager(this).adddata(Name,Age,City,Gender);

        name.getEditText().setText("");
        age.getEditText().setText("");
        city.getEditText().setText("");
        gender.getEditText().setText("");

        Toast.makeText(this,res,Toast.LENGTH_SHORT).show();;

    }


}