package com.example.login_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NoticeBoard extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");
    TextView notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        notice = findViewById(R.id.notice);

//        databaseReference.child("notice").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.child("notice").setValue(">> No Tobacco\n\n>> No gum or food allowed\n\n>> No bare feet are allowed on the floor\n\n>> Shirts must be worn at all time\n\n>> Sand/Mud must be removed from shoes before entering the facilities\n\n>> One can use the gym on weekdays between 8am to 5pm for 1 hour");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        databaseReference.child("notice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String getTerms = snapshot.getValue(String.class);
                notice.setText(getTerms);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}