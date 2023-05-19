package com.example.login_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class create_account extends AppCompatActivity {

    TextView gotosignin;
    EditText userid,username,password,password2;
    Button savebtn;
//    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userid = findViewById(R.id.userid);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        gotosignin = (TextView) findViewById(R.id.gotosingin);
        savebtn = findViewById(R.id.savebtn);
//        DB = new DBHelper(this);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");


        gotosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(create_account.this, MainActivity.class);
                startActivity(intent);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userid.getText().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();
                String name = username.getText().toString();

                if(TextUtils.isEmpty(id) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(pass2) || TextUtils.isEmpty(name)){
                    Toast.makeText(create_account.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(pass2)){
                        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(id)){
                                    Toast.makeText(create_account.this, "User id already exists", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    databaseReference.child("users").child(id).child("name").setValue(name);
                                    databaseReference.child("users").child(id).child("password").setValue(pass);
                                    Toast.makeText(create_account.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(create_account.this, "Registration Failed! Please try again.", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    else{
                        Toast.makeText(create_account.this, "Password and Confirm Password aren't matching!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}