package com.example.databaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databaseproject.models.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

public class FetchActivity extends AppCompatActivity {
     TextView result_tv;
     Button Fetch;

     FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        result_tv= findViewById(R.id.data);
        Fetch=findViewById(R.id.fetch);
        db=FirebaseFirestore.getInstance();
        Fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
getData();
            }
        });
    }
    public void getData(){
db.collection("Order")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String resultStr = "";

                    for (DocumentSnapshot document : task.getResult()) {
                        Order order = document.toObject(Order.class);
                        resultStr += "Item:" + order.getItemName() + "\nQty:" + order.getItemQty() + "\n\n";
                    }
                    result_tv.setText(resultStr);
                }
            }
        })
           .addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
               }
           });
        }
    }
