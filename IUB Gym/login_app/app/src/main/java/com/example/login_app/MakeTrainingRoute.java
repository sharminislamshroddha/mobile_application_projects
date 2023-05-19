package com.example.login_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MakeTrainingRoute extends AppCompatActivity {
    EditText terms,userID;
    Button savebtn;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_training_route);

        terms = findViewById(R.id.terms);
        userID = findViewById(R.id.userID);
        savebtn = findViewById(R.id.savebtn);

        terms.setText("> Push-Ups: 3 Set * 6-8 Reps\n" +
                       "> Pull_Ups: 3 Set * 6-8 Reps\n"+
                        "> Dumbbell Flyes: 2 Set * 10-12 Reps\n"+
                "> Push-Ups: 3 Set * 6-8 Reps\n" +
                "> Pull_Ups: 3 Set * 6-8 Reps\n"+
                "> Dumbbell Flyes: 2 Set * 10-12 Reps");

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userID.getText().toString();

                if(TextUtils.isEmpty(id)){
                    Toast.makeText(MakeTrainingRoute.this, "User ID is Required.", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.hasChild(id)) {
                                if (true) {
                                databaseReference.child("Users").child(id).child("workout_plan").setValue(terms.getText().toString());
                                Toast.makeText(MakeTrainingRoute.this, "Updated Successfully.", Toast.LENGTH_SHORT).show();

                                userID.setText(null);
                                terms.setText("> Push-Ups: 3 Set * 6-8 Reps\n" +
                                            "> Pull_Ups: 3 Set * 6-8 Reps\n"+
                                            "> Dumbbell Flyes: 2 Set * 10-12 Reps\n"+
                                            "> Push-Ups: 3 Set * 6-8 Reps\n" +
                                            "> Pull_Ups: 3 Set * 6-8 Reps\n"+
                                            "> Dumbbell Flyes: 2 Set * 10-12 Reps");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MakeTrainingRoute.this, "Database Error! Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
    }
}