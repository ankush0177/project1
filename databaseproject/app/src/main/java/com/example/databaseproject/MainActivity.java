package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;


import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


private static int SPLASH_TIME_OUT= 4000;
    FirebaseFirestore db;
//FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("SPLASH SCREEN");

        new Handler().postDelayed(new Runnable(){

          @Override
          public void run(){
              Intent homeIntent= new Intent(MainActivity.this, LoginActivity.class);
              startActivity(homeIntent);
              finish();
            }
        },SPLASH_TIME_OUT);

    }
}



