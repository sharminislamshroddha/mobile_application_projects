package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class user_page extends AppCompatActivity {
    private static String passID,passName;
    Button signoutbtn;
    TextView id, name;
    public static void passUserID(String id, String name){
        passID = id;
        passName = name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        signoutbtn = findViewById(R.id.signoutbtn);
        id = findViewById(R.id.userid);
        name = findViewById(R.id.username);

        id.setText("ID: "+passID);
        name.setText("Name: "+passName);

        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


}