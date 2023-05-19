package com.example.login_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ApproveApplication extends AppCompatActivity {
    Button add;
    TextView id,name,phone;
    LinearLayout layout;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_application);

        add = findViewById(R.id.add);

        layout = findViewById(R.id.container);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("Applicant").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data : snapshot.getChildren()){
                            System.out.println(data);
                            String name = data.child("name").getValue(String.class);
                            String id = data.child("name").getValue(String.class);
//                            String id = data.getValue(String.class);
                            String phn = data.child("phoneNumber").getValue(String.class);
                            if(data.child("status").getValue(String.class).equals("no")) {
                                addCard(name, id, phn);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void addCard(String name,String idd,String phn) {
        final View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView nameView = view.findViewById(R.id.name);
        TextView id = view.findViewById(R.id.id);
        TextView phone =view.findViewById(R.id.phone);

        Button approve = view.findViewById(R.id.approve);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText("Name: " + name);
        id.setText("ID: " + idd);
        phone.setText("Phone: " + phn);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("Applicant").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        databaseReference.child("Applicant").child(idd).child("status").setValue("yes");

                        databaseReference.child("Users").child(idd).child("name").setValue(snapshot.child(idd).child("name").getValue(String.class));
                        databaseReference.child("Users").child(idd).child("password").setValue(snapshot.child(idd).child("password").getValue(String.class));
                        databaseReference.child("Users").child(idd).child("phoneNumber").setValue(snapshot.child(idd).child("phoneNumber").getValue(String.class));
                        databaseReference.child("Users").child(idd).child("address").setValue(snapshot.child(idd).child("address").getValue(String.class));
                        databaseReference.child("Users").child(idd).child("email").setValue(snapshot.child(idd).child("email").getValue(String.class));
                        databaseReference.child("Users").child(idd).child("dob").setValue(snapshot.child(idd).child("dob").getValue(String.class));
                        databaseReference.child("Users").child(idd).child("semester").setValue(snapshot.child(idd).child("semester").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//
                layout.removeView(view);
            }
        });

        layout.addView(view);
    }



}
