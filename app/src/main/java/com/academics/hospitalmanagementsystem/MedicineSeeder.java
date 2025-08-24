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
        medicines.add(new Medicine("Vicks", "Topical ointment for cold, cough and congestion relief", 55.0, "vicks_balm"));
        medicines.add(new Medicine("Danzen DS", "Used for swelling and inflammation (anti-inflammatory)", 120.0, "danzen_tab"));
        medicines.add(new Medicine("Flagyl", "Used to treat bacterial and parasitic infections", 90.0, "flagyl_tab"));
        medicines.add(new Medicine("Ethrocin", "Antibiotic, used for chest and throat infections", 140.0, "ethrocin_tab"));
        medicines.add(new Medicine("Nise", "Pain reliever and anti-inflammatory", 75.0, "nise_tab"));
        medicines.add(new Medicine("Sofvasc", "Used to treat high blood pressure and chest pain", 110.0, "sofvasc_tab"));
        medicines.add(new Medicine("Nuberol", "Pain reliever and muscle relaxant", 95.0, "nuberol_tab"));
        medicines.add(new Medicine("Gravinate", "Used for nausea, vomiting, and motion sickness", 65.0, "gravinate_tab"));
        medicines.add(new Medicine("Always", "Menstrual Pads", 300.0, "always_pad"));
        medicines.add(new Medicine("Zantac", "Used to reduce stomach acid and treat ulcers", 80.0, "zantac_tab"));
        medicines.add(new Medicine("Azomax", "Antibiotic, used for respiratory and skin infections", 150.0, "azomax_tab"));
        medicines.add(new Medicine("Vibramycin", "Antibiotic, used for acne, chest and urinary infections", 160.0, "vibramycin_tab"));
        medicines.add(new Medicine("Domel", "Used to relieve nausea, vomiting, and bloating", 70.0, "domel_tab"));
        medicines.add(new Medicine("Duragesic", "Strong pain reliever (fentanyl patch)", 200.0, "duragesic_tab"));
        medicines.add(new Medicine("No-spa", "Used to relieve stomach cramps and spasms", 85.0, "no_spa_tab"));
        medicines.add(new Medicine("Softin", "Used to treat allergies (antihistamine)", 60.0, "softin_tab"));
        medicines.add(new Medicine("Rumadol C", "Pain reliever, used for fever and body aches", 100.0, "rumadol_c_tab"));
        medicines.add(new Medicine("Surbex Z", "Multivitamin supplement for energy and immunity", 130.0, "surbex_z_tab"));
        medicines.add(new Medicine("Viscal D", "Calcium and Vitamin D supplement for bone health", 115.0, "viscal_d_tab"));
        medicines.add(new Medicine("Xantix", "Used for acidity, reflux, and heartburn", 90.0, "xantix_tab"));
        medicines.add(new Medicine("Voltral Gel", "Topical gel for joint and muscle pain relief", 150.0, "voltral_gel"));
        return medicines;
    }
}
