package com.example.login_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TermsAndCondition extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");
    TextView terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);

        terms = findViewById(R.id.terms);

//        databaseReference.child("conditions").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.child("conditions").setValue(">> No Tobacco\n\n>> No gum or food allowed\n\n>> No bare feet are allowed on the floor\n\n>> Shirts must be worn at all time\n\n>> Sand/Mud must be removed from shoes before entering the facilities\n\n>> One can use the gym on weekdays between 8am to 5pm for 1 hour");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        databaseReference.child("conditions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String getTerms = snapshot.getValue(String.class);
                terms.setText(getTerms);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

