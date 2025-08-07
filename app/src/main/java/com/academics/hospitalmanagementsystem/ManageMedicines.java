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

        Medicine med1 = new Medicine("Panadol", "Pain Reliever and Fever Reducer", 50.0, "panadol_tab");
        medRef.push().setValue(med1);

        Log.d("ManageMedicines","Medicine Add/Delete Operation Triggered");
        finish();
    }
}
