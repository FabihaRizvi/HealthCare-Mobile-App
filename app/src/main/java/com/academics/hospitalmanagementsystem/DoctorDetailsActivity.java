package com.academics.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        TextView title = findViewById(R.id.DDtitle);
        Intent intent = getIntent();
        String doctorTitle = intent.getStringExtra("Title");
        title.setText(doctorTitle);

        Button btn = findViewById(R.id.DDbackButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
    }
}