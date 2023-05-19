package com.example.login_app;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.material.textfield.TextInputLayout;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class CreateTrainerAccount extends AppCompatActivity {

    EditText userID, userName, userEmail, userAddress, DoB, phone, password1, password2;
    Button savebtn;
    TextInputLayout dropdownGenderType;
    AutoCompleteTextView autoCompleteTextView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trainer_account);

        userID = findViewById(R.id.userID);
        userName = findViewById(R.id.username);
        userEmail = findViewById(R.id.userEmail);
        userAddress = findViewById(R.id.userAddress);
        DoB = findViewById(R.id.dob);
        phone = findViewById(R.id.userPhone);
        password1 = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        savebtn = findViewById(R.id.savebtn);
        dropdownGenderType = findViewById(R.id.dropdownUserType);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        String[] items = getResources().getStringArray(R.array.Gender);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(CreateTrainerAccount.this, R.layout.drop_down_item, items);
        autoCompleteTextView.setAdapter(itemAdapter);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userID.getText().toString();
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String address = userAddress.getText().toString();
                String dob = DoB.getText().toString();
                String phn = phone.getText().toString();
                String pass1 = password1.getText().toString();
                String pass2 = password2.getText().toString();

                final String[] userType = new String[1];

                if(TextUtils.isEmpty(id) || TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address)
                        || TextUtils.isEmpty(dob) || TextUtils.isEmpty(phn)
                        || TextUtils.isEmpty(pass1) || TextUtils.isEmpty(pass2)){
                    Toast.makeText(CreateTrainerAccount.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass1.equals(pass2)){
                        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(id)){
                                    Toast.makeText(CreateTrainerAccount.this, "Users id already exists", Toast.LENGTH_SHORT).show();
                                    userID.setText(null);
                                }
                                else{
                                    databaseReference.child("Users").child(id).child("name").setValue(name);
                                    databaseReference.child("Users").child(id).child("password").setValue(pass1);
                                    databaseReference.child("Users").child(id).child("phoneNumber").setValue(phn);
                                    databaseReference.child("Users").child(id).child("address").setValue(address);
                                    databaseReference.child("Users").child(id).child("email").setValue(email);
                                    databaseReference.child("Users").child(id).child("dob").setValue(dob);

                                    Toast.makeText(CreateTrainerAccount.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                    DoB.setText(null);
                                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            phone.setText((String)parent.getItemAtPosition(position));
                                            databaseReference.child("Users").child(userID.getText().toString()).child("gender").setValue(phone.getText().toString());
                                        }
                                    });
                                    phone.setText(null);
                                    userID.setText(null);
                                    userName.setText(null);
                                    userEmail.setText(null);
                                    userAddress.setText(null);
                                    password1.setText(null);
                                    password2.setText(null);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(CreateTrainerAccount.this, "Failed! Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(CreateTrainerAccount.this, "Password and Confirm Password aren't matching!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}