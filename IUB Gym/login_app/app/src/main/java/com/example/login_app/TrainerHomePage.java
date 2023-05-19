package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TrainerHomePage extends AppCompatActivity {

    Button logoutBtn,approveApplication,updateNoticeBoard, training,takeAttendance;
    TextView username,userID;

    static String passID="",passName="";
    public static void passUserInfo(String id, String name){
        passID = id;
        passName = name;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_home_page);

        logoutBtn = findViewById(R.id.logoutBtn);
        approveApplication = findViewById(R.id.approveApplication);
        updateNoticeBoard = findViewById(R.id.updateNoticeBoard);
        training = findViewById(R.id.training);
        takeAttendance = findViewById(R.id.takeAttendance);

        username = findViewById(R.id.username);
        userID = findViewById(R.id.userID);

        username.setText(passName);
        userID.setText(passID);

        updateNoticeBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainerHomePage.this, UpdateNoticeBoard.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainerHomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainerHomePage.this, MakeTrainingRoute.class);
                startActivity(intent);
            }
        });

        approveApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainerHomePage.this, ApproveApplication.class);
                startActivity(intent);
            }
        });

        takeAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainerHomePage.this, TakeStudentAttendance.class);
                startActivity(intent);
            }
        });
    }
}

