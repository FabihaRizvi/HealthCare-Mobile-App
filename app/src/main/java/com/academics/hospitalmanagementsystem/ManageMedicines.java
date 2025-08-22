package com.academics.hospitalmanagementsystem;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ManageMedicines extends AppCompatActivity{
    DatabaseReference medRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        medRef = FirebaseDatabase.getInstance().getReference("Medicines");

        medRef.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                addMedicine(new Medicine("Panadol", "Pain Reliever and Fever Reducer", 50.0, "panadol_tab"));
                addMedicine(new Medicine("Brufen", "Anti inflammatory", 75.0, "brufen_tab"));
                addMedicine(new Medicine("Rigix", "Anti allergy", 25.0, "rigix_tab"));
                addMedicine(new Medicine("Augmentin", "Antibiotic", 120.0, "augmentin_tab"));
                addMedicine(new Medicine("Disprin", "Headache relief", 30.0, "disprin_tab"));

                Log.d("ManageMedicines", "Medicines inserted successfully");
            }
            else {
                Log.e("ManageMedicines", "Failed to clear Medicines");
            }
            finish();
        });

    }

    private void addMedicine(Medicine medicine){
        String key = medRef.push().getKey();
        medRef.child(key).setValue(medicine)
                .addOnSuccessListener(aVoid -> Log.d("ManageMedicines", "Added: "+medicine.name))
                .addOnFailureListener(e -> Log.d("ManageMedicines", "Error:  "+ e.getMessage()));
    }
}
