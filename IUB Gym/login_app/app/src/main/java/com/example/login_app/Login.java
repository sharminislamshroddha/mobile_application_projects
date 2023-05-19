package com.example.login_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText userID, password;
    TextInputLayout dropdownUserType;
    AutoCompleteTextView autoCompleteTextView;
    Button loginbtn;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userID = findViewById(R.id.userID);
        password = findViewById(R.id.password);
        dropdownUserType = findViewById(R.id.dropdownUserType);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        loginbtn = findViewById(R.id.loginbtn);

        String[] items = getResources().getStringArray(R.array.UserType);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(Login.this, R.layout.drop_down_item, items);
        autoCompleteTextView.setAdapter(itemAdapter);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userID.getText().toString();
                String pass = password.getText().toString();
//                final String[] userType = new String[1];

//                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        userType[0] = (String) parent.getItemAtPosition(position);
//                    }
//                });


                if (TextUtils.isEmpty(id) && TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(id)) {
                                final String getPassword = snapshot.child(id).child("password").getValue(String.class);
                                final String getName = snapshot.child(id).child("name").getValue(String.class);

                                if (getPassword.equals(pass)) {
                                    if (Integer.parseInt(id)<1000) {
                                        StudentHomePage.passUserInfo(id, getName);
                                        Intent intent = new Intent(Login.this, StudentHomePage.class);
                                        startActivity(intent);
                                    }else if (Integer.parseInt(id)>2000) {
//                                        AdminHomePage.passUserInfo(id, getName);
//                                        Intent intent = new Intent(Login.this, AdminHomePage.class);
//                                        startActivity(intent);
                                    }else if (Integer.parseInt(id)>1000) {
//                                        TrainerHomePage.passUserInfo(id, getName);
                                        Intent intent = new Intent(Login.this, TrainerHomePage.class);
                                        startActivity(intent);
                                    }
                                }
                                else {
                                    userID.setText(null);
                                    password.setText(null);
                                    Toast.makeText(Login.this, "ID Password combination is wrong!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                userID.setText(null);
                                password.setText(null);
                                Toast.makeText(Login.this, "ID Password combination is wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Login.this, "Database Error! Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}