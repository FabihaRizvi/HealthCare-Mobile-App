package com.academics.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class DoctorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        ListView listView = findViewById(R.id.DDlist);
        String doctorTitle = getIntent().getStringExtra("Title");
        TextView title = findViewById(R.id.DDtitle);
        title.setText(doctorTitle);

        String category = "";
        switch (doctorTitle) {
            case "General Physician":
                category = "general_physician";
                break;
            case "Dentist":
                category = "dentist";
                break;
            case "Dietician":
                category = "dietician";
                break;
            case "Surgeon":
                category = "surgeon";
                break;
            case "Cardiologist":
                category = "cardiologist";
                break;
            case "Psychiatrist":
                category = "psychiatrist";
                break;
        }

        ArrayList<String> doctorList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, doctorList);
        listView.setAdapter(adapter);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(category);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doctorList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Doctor doctor = snap.getValue(Doctor.class);
                    if (doctor != null) {
                        String details = "Name: " + doctor.name +
                                "\nGender: " + doctor.gender +
                                "\nHospital: " + doctor.hospital +
                                "\nExperience: " + doctor.experience + " yrs" +
                                "\nFee: $" + doctor.fee;
                        doctorList.add(details);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorDetailsActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn = findViewById(R.id.DDbackButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

    }
}