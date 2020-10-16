package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    // declaration here
    //private TextView Attempts; // Textview widget
    private EditText un; // edittext1 widget
    private EditText pwd; // edittext2 widget
    private Button login; //button widget
    private Button Signup; // button widget

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("LOGIN PAGE");

        un= findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        login = findViewById(R.id.button);
        Signup= findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(un.getText().toString(), pwd.getText().toString());
            }
        });
     Signup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Toast.makeText(HomeActivity.this, "Signup has been Clicked", Toast.LENGTH_SHORT).show();
             Intent intent= new Intent(HomeActivity.this, signup_activity.class);
             startActivity(intent);
         }
     });
    }
    private void validate(String USERNAME,String PASSWORD) {
        if ((USERNAME.equals("admin")) && (PASSWORD.equals("1234"))) {
            Toast.makeText(HomeActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, ListActivity.class);
            startActivity(intent);
        } else if((USERNAME.isEmpty()) && (PASSWORD.isEmpty()))

            Toast.makeText(HomeActivity.this, "Enter Mandatory Fields", Toast.LENGTH_SHORT).show();
        }

    }



