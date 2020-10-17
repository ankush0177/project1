package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup_activity extends AppCompatActivity {

    //Declaration here!!!
    TextView t;
    EditText Fn;
    EditText Ln;
    EditText Email;
    EditText Contact;
    EditText Password;
    EditText Confirm_Password;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        getSupportActionBar().setTitle("REGISTRATION PAGE");

        t=findViewById(R.id.tv_logo);
        Fn= findViewById(R.id.et_firstname);
        Ln= findViewById(R.id.et_lastname);
        Email=findViewById(R.id.et_email);
        Contact=findViewById(R.id.et_Phoneno);
        Password=findViewById(R.id.et_password);
        Confirm_Password= findViewById(R.id.et_repassword);
        Register= findViewById(R.id.btn_register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Contact.getText().toString());
            }
        });

    }
    private void validate(String EMAIL,String CONTACT){
        if((EMAIL.isEmpty()) || (CONTACT.isEmpty())){
            Toast.makeText(signup_activity.this, "Enter Mandatory Fields", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(signup_activity.this, "Signup Successfull", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(signup_activity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}