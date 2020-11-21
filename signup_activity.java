package com.example.databaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databaseproject.models.Order;
import com.example.databaseproject.models.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup_activity extends AppCompatActivity {

    //Declaration here!!!
    TextView t;
    EditText Fn;
    EditText Ln;
    EditText Email;
    //EditText Contact;
    EditText Password;
    EditText Confirm_Password;
    Button Register;
    FirebaseAuth fAuth;
    public static final String TAG = "TAG";
    FirebaseFirestore db;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        getSupportActionBar().setTitle("REGISTRATION PAGE");

        t = findViewById(R.id.tv_logo);
        Fn = findViewById(R.id.et_firstname);
        Ln = findViewById(R.id.et_lastname);
        Email = findViewById(R.id.et_email);
       // Contact = findViewById(R.id.et_Phoneno);
        Password = findViewById(R.id.et_password);
        Confirm_Password = findViewById(R.id.et_repassword);
        Register = findViewById(R.id.btn_register);
        fAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
//if(fAuth.getCurrentUser() != null)
//{
//    startActivity(new Intent(getApplicationContext(), MainActivity.class));
 //   finish();
//}
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                validate(Email.getText().toString(), Password.getText().toString());
                final String fst_name= Fn.getText().toString();
                String Last_name=Ln.getText().toString();
                final String email_id=Email.getText().toString();
                String PWD=Password.getText().toString();
                addData(fst_name, Last_name,email_id,PWD);

                if(TextUtils.isEmpty(email_id)){
                    Email.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(PWD)){
                    Password.setError("Password is Required.");
                    return;
                }

                if(PWD.length() < 6){
                    Password.setError("Password Must be >= 6 Characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email_id,PWD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                        //send verification link
                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(signup_activity.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });
                            Toast.makeText(signup_activity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = db.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("firstname",fst_name);
                            user.put("email",email_id);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{

                            Toast.makeText(signup_activity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
    public void addData(String FirstName, String LastName, String Email, String Password){
        Registration registration = new Registration(FirstName, LastName, Email, Password);
        db.collection("Registration")
                .add(registration)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Order Recorded!!!", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error:"+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
   private void validate(String EMAIL, String PASSWORD){
        if((EMAIL.isEmpty()) || (PASSWORD.isEmpty()))
            Toast.makeText(signup_activity.this, "Enter Mandatory Fields", Toast.LENGTH_SHORT).show();
       else{
            Toast.makeText(signup_activity.this, "Signup Successfull", Toast.LENGTH_SHORT).show();
           Intent intent= new Intent(signup_activity.this, LoginActivity.class);
            startActivity(intent);
       }
}
}
