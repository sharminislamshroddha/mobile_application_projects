package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.txv_id);

        Button btnCount= findViewById(R.id.count_id);
        Button btnToast= findViewById(R.id.toast_id);

        btnCount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countUp();
            }
        });
        btnToast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              showToast();
            }
        });
    }
    public void showToast() {
        mCount = 0;
        mShowCount.setText(Integer.toString(mCount));
        Toast toast = Toast.makeText(MainActivity.this, "Hello Toast @ Shroddha", Toast.LENGTH_SHORT);
        toast.show();
    }
    @SuppressLint("SetTextI18n")
    public void countUp() {
        mCount++;
        mShowCount.setText(Integer.toString(mCount));
    }
}