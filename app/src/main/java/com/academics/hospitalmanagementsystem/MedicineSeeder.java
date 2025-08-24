package com.academics.hospitalmanagementsystem;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MedicineSeeder {

    public static void seedMedicines() {
        DatabaseReference medRef = FirebaseDatabase.getInstance().getReference("Medicines");

        medRef.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Medicine> medicineList = getAllMedicines();
                for (Medicine medicine : medicineList) {
                    addMedicine(medRef, medicine);
                }
            }
        });
    }

    private static void addMedicine(DatabaseReference medRef, Medicine medicine){
        String key = medRef.push().getKey();
        medRef.child(key).setValue(medicine)
                .addOnSuccessListener(aVoid -> Log.d("ManageMedicines", "Added: "+medicine.name))
                .addOnFailureListener(e -> Log.d("ManageMedicines", "Error:  "+ e.getMessage()));
    }

    private static List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine("Panadol", "Pain Reliever", 50.0, "panadol_tab"));
        medicines.add(new Medicine("Brufen", "Anti inflammatory", 75.0, "brufen_tab"));
        medicines.add(new Medicine("Rigix", "Anti allergy", 25.0, "rigix_tab"));
        medicines.add(new Medicine("Augmentin", "Antibiotics, killing the bacteria that are causing infections", 150.0, "augmentin_tab"));
        medicines.add(new Medicine("Tonoflex P", "Muscles Relaxant", 45.0, "tonoflex_tab"));
        medicines.add(new Medicine("Disprin", "For the relief of headaches", 40.0, "disprin_tab"));
        medicines.add(new Medicine("Ponstan", "To relieve the symptoms of period pain and treat heavy periods", 15.0, "ponstan_tab"));
        medicines.add(new Medicine("Motilium", "Antiemetic and a Prokinetic ", 35.0, "motilium_tab"));
        return medicines;
    }


}
