package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StudentHomePage extends AppCompatActivity {

    private static String passID,passName,usertype;
    public static void passUserInfo(String id, String name){
        passID = id;
        passName = name;
//        usertype = usertype;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);
    }
}