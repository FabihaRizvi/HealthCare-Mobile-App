package com.academics.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        TextView title = findViewById(R.id.DDtitle);
        ListView listView = findViewById(R.id.DDlist);
        String doctorTitle = getIntent().getStringExtra("Title");
        String category = "";
        if (doctorTitle != null) {
            title.setText(doctorTitle);
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
                default:
                    ToastHelper.showToast(this, "Unknown doctor category");
                    return;
            }
        } else {
            ToastHelper.showToast(this, "No category provided");
            return;
        }

        ArrayList<String> doctorList = new ArrayList<>();
        ArrayList<Doctor> doctorObjects = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.doctor_details_list, R.id.doctorDetailsText, doctorList);
        listView.setAdapter(adapter);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(category).child("doctors");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doctorList.clear();
                doctorObjects.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Doctor doctor = snap.getValue(Doctor.class);
                    if (doctor != null) {
                        doctorObjects.add(doctor);
                        String details = "Name: " + doctor.name +
                                "\nGender: " + doctor.gender +
                                "\nHospital: " + doctor.hospital +
                                "\nExperience: " + doctor.experience + " yrs" +
                                "\nFee: $" + doctor.fee;
                        doctorList.add(details);
                    }
                }
                adapter.notifyDataSetChanged();

                listView.setOnItemClickListener((parent, view, position, id) -> {
                    Doctor selectedDoctor = doctorObjects.get(position);

                    Intent intent = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                    intent.putExtra("doctorName", selectedDoctor.name);
                    intent.putExtra("hospitalName", selectedDoctor.hospital);
                    startActivity(intent);
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("FIREBASE_ERROR", error.getMessage());
                ToastHelper.showToast(DoctorDetailsActivity.this, "Data load failed");
            }
        });

        TextView backTextView = findViewById(R.id.ddbackOptiontextView);
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

    }
}