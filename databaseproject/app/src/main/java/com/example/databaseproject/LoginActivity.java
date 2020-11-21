package com.example.databaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databaseproject.models.Account;
import com.example.databaseproject.models.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    // declaration here
    //private TextView Attempts; // Textview widget
    private EditText email; // edittext1 widget
    private EditText pwd; // edittext2 widget
    private Button login; //button widget
    private TextView Signup; // textview widget

    FirebaseAuth fAuth;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("LOGIN PAGE");

        email = findViewById(R.id.email);
        pwd = findViewById(R.id.password);
        login = findViewById(R.id.button);
        Signup = findViewById(R.id.textviewforsignup);
        fAuth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v2) {
                                          validate(email.getText().toString(), pwd.getText().toString());
                                         String email_id = email.getText().toString();
                                         String pass_word = pwd.getText().toString();
                                         addinfo(email_id, pass_word);


                                         if (TextUtils.isEmpty(email_id)) {
                                             email.setError("Email is Required");
                                             return;
                                         }
                                         if (TextUtils.isEmpty(pass_word)) {
                                             pwd.setError("Password is Required");
                                             return;
                                         }
                                         if (pass_word.length() < 6) {
                                             pwd.setError("Password must be >= 6 characters");
                                             return;
                                         }

                                         fAuth.signInWithEmailAndPassword(email_id, pass_word).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                             @Override
                                             public void onComplete(@NonNull Task<AuthResult> task) {

                                                 if (task.isSuccessful()) {
                                                     Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                                     Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                                                     startActivity(intent);
                                                 } else {
                                                     Toast.makeText(LoginActivity.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                 }
                                             }

                                         });
                                     }
                                 });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Toast.makeText(LoginActivity.this, "Signup has been Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, signup_activity.class);
               // startActivity(intent);
                startActivity(new Intent(getApplicationContext(),signup_activity.class));
            }
        });
    }
    public void addinfo(String email_id, String pass_word) {
        Account ac = new Account(email_id, pass_word);
        db.collection("Account")
                .add(ac)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Data Recorded!!!", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void validate(String EMAIL, String PASSWORD) {
        if ((EMAIL.equals("raghav@gmail.com")) && (PASSWORD.equals("1234567"))) {
            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ListActivity.class);
            startActivity(intent);
        } else if ((EMAIL.isEmpty()) && (PASSWORD.isEmpty()))

            Toast.makeText(LoginActivity.this, "Enter Mandatory Fields", Toast.LENGTH_SHORT).show();


    }

}

