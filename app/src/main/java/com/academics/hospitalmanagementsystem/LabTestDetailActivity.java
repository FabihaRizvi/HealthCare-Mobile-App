package com.academics.hospitalmanagementsystem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.*;
import java.util.ArrayList;

public class LabTestDetailActivity extends AppCompatActivity {

    TextView titleTextView;
    ListView listView;
    ArrayList<Lab> labList;
    LabAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        titleTextView = findViewById(R.id.DDtitle);
        listView = findViewById(R.id.DDlist);
        labList = new ArrayList<>();

        String testName = getIntent().getStringExtra("test_name");

        if (testName != null) {
            titleTextView.setText(testName);

            String firebaseNode = mapTestNameToFirebaseNode(testName);

            if (firebaseNode == null) {
                Toast.makeText(this, "No Firebase node found for this test", Toast.LENGTH_SHORT).show();
                return;
            }
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseNode).child("labs");

            adapter = new LabAdapter(this, labList);
            listView.setAdapter(adapter);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    labList.clear();
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        Lab lab = snap.getValue(Lab.class);
                        if (lab != null) {
                            labList.add(lab);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LabTestDetailActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Test name not received", Toast.LENGTH_SHORT).show();
        }

        TextView backOptionLab = findViewById(R.id.ddbackOptiontextView);
        backOptionLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailActivity.this, LabTestActivity.class));
            }
        });
    }
    private String mapTestNameToFirebaseNode (String testName){
        switch (testName) {
            case "Blood Test":
                return "blood_test_labs";
            case "X-Ray":
                return "xray";
            case "Diabetes Test":
                return "diabetes_test";
            case "CBC":
                return "cbc";
            case "Urine Routine Test":
                return "urine_routine_test";
            case "Ultrasound":
                return "ultrasound";
            default:
                return null;
        }
    }
}

