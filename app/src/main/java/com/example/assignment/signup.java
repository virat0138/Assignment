package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {
    private EditText Email , Password, Name;
    private ImageButton signup;
    private Button signin;

    private FirebaseAuth Auth;
    private  FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        Name = findViewById(R.id.name);

        Auth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this , MainActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }
    private void createUser(){
        String email = Email.getText().toString();
        String pass = Password.getText().toString();
        String name = Name.getText().toString();

        if(name.isEmpty()){
            Name.setError("Enter your Name");
            Name.requestFocus();
            return;
        }

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                Auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signup.this , MainActivity.class));
                                finish();

                                if(task.isSuccessful()){
                                    user User = new user(name);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(signup.this, "User has been Registered", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(signup.this, "Failed to Registered", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else{
                                    Toast.makeText(signup.this, "Failed to Registered", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signup.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Password.setError("Empty Fields");
            }
        }else if(email.isEmpty()){
            Email.setError("Empty Fields");
        }else{
            Email.setError("Pleas Enter Correct Email");
        }
    }

}