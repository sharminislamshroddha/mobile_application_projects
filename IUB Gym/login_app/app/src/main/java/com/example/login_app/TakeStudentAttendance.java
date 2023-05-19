package com.example.login_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.*;

public class TakeStudentAttendance extends AppCompatActivity {

    EditText userID,startingTime,endingTime;
    Button submitBtn;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_student_attendance);

        userID = findViewById(R.id.userID);
        startingTime = findViewById(R.id.startingTime);
        endingTime = findViewById(R.id.endingTime);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=userID.getText().toString();
                String startTime = startingTime.getText().toString();
                String endTime = endingTime.getText().toString();

                if(TextUtils.isEmpty(id) || TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime))
                {
                    Toast.makeText(TakeStudentAttendance.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Attendance").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if(snapshot.hasChild(id)){
//                                Toast.makeText(TakeStudentAttendance.this, "Student Attendance already taken", Toast.LENGTH_SHORT).show();
//                                userID.setText(null);
//                            }
//                            else {

                                databaseReference.child("Attendance").child(id).child("startTime").setValue(startTime);
                                databaseReference.child("Attendance").child(id).child("endTime").setValue(endTime);
                                databaseReference.child("Attendance").child(id).child("date").setValue("26/08/2022");
//                                databaseReference.child("Attendance").child(id).child("date").setValue(LocalDate.now().toString());

                                Toast.makeText(TakeStudentAttendance.this, "Attendance Taken Successfully", Toast.LENGTH_SHORT).show();
                                finish();

                                userID.setText(null);
                                startingTime.setText(null);
                                endingTime.setText(null);
//                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }
        });
    }
}