package com.academics.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        TextView exit = findViewById(R.id.backOption);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView FDGeneralPhysician = findViewById(R.id.FDGeneralPhysician);
        FDGeneralPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("Title", "General Physician");
                startActivity(intent);
            }
        });

        CardView FDDentist = findViewById(R.id.FDDentist);
        FDDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("Title", "Dentist");
                startActivity(intent);
            }
        });

        CardView FDDietician = findViewById(R.id.FDDietician);
        FDDietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("Title", "Dietician");
                startActivity(intent);
            }
        });

        CardView FDSurgeon = findViewById(R.id.FDSurgeon);
        FDSurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("Title", "Surgeon");
                startActivity(intent);
            }
        });

        CardView FDCardiologist = findViewById(R.id.FDCardiologist);
        FDCardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("Title", "Cardiologist");
                startActivity(intent);
            }
        });

        CardView FDPsychiatrist = findViewById(R.id.FDPsychiatrist);
        FDPsychiatrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("Title", "Psychiatrist");
                startActivity(intent);
            }
        });
    }
}