package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener  {

    Button ContinueButton;
    Button FetchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setTitle("HOME PAGE");

        ContinueButton = findViewById(R.id.ContinueBtn);
        FetchButton = findViewById(R.id.FetchBtn);

        ContinueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v1) {
                openContinue();
            }
        });
        FetchButton.setOnClickListener( this);
    }
private void openContinue(){

        Intent intent = new Intent( this, ContinueActivity.class);
        startActivity(intent);
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ContinueBtn:
               Toast.makeText(ListActivity.this, "Continue has been clicked", Toast.LENGTH_SHORT).show();
              Intent intent = new Intent(this.getApplicationContext(), ContinueActivity.class);
               this.startActivity(intent);
               break;
            case R.id.FetchBtn:
                Toast.makeText(ListActivity.this, "Fetch the more information", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this.getApplicationContext(), FetchActivity.class);
                this.startActivity(intent1);
                break;
        }
    }

}

