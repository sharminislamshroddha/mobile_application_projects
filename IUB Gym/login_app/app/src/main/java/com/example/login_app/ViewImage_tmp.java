package com.example.login_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewImage_tmp extends AppCompatActivity {
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String url = "";
    ImageView image;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image_tmp);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sign-in-app-c918e-default-rtdb.firebaseio.com/");
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sign-in-app-c918e.appspot.com");
        image = (ImageView)findViewById(R.id.image);
        txt = (TextView)findViewById(R.id.txt);

        databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Toast.makeText(ViewImage_tmp.this, "Yeeeeeeeeee", Toast.LENGTH_SHORT).show();
                url = snapshot.child("Image").child("imageURL").getValue(String.class);

                txt.setText(url);

                Picasso.with(ViewImage_tmp.this)
                        .load(url)
                        .into(image);
//                Picasso.with(ViewImage_tmp.this)
//                        .load("https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png")
//                        .resize(300, 300)
//                        .into(image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}