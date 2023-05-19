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
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    TextView forgotPassword, createAccount;
    EditText userid,password;
    Button loginbtn;
//    DBHelper DB;
//    FirebaseFirestore firetore;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DB = new DBHelper(this);
        forgotPassword = (TextView)findViewById(R.id.forgot_pass);
        createAccount = (TextView)findViewById(R.id.create_account);
        userid = findViewById(R.id.userid);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "yeeeeeeeee", Toast.LENGTH_SHORT).show();
                userid.setText(null);
                password.setText(null);
                Intent intent = new Intent(MainActivity.this, forgot_password.class);
                startActivity(intent);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid.setText(null);
                password.setText(null);
//                Toast.makeText(MainActivity.this, "yaaahoooooo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), create_account.class);
                startActivity(intent);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userid.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(id) || TextUtils.isEmpty(pass)){
                    Toast.makeText(MainActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(id)){
                                final String getPassword = snapshot.child(id).child("password").getValue(String.class);
                                final String name = snapshot.child(id).child("name").getValue(String.class);

                                if(getPassword.equals(pass)){
                                    user_page.passUserID(id,name);
                                    Intent intent = new Intent(MainActivity.this, user_page.class);
                                    startActivity(intent);
                                }
                                else{
                                    userid.setText(null);
                                    password.setText(null);
                                    Toast.makeText(MainActivity.this, "ID Password combination is wrong!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                userid.setText(null);
                                password.setText(null);
                                Toast.makeText(MainActivity.this, "ID Password combination is wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, "Database Error! Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}