package com.academics.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BuyMedicineActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnOrder;
    List<Medicine> medicines;
    List<Medicine> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        recyclerView = findViewById(R.id.medicineRecyclerView);
        btnOrder = findViewById(R.id.btnOrder);

        medicines = new ArrayList<>();
        cart = new ArrayList<>();

        medicines.add(new Medicine("Panadol", "Pain reliever and fever reducer", 50.0, R.drawable.panadol_tab));
        medicines.add(new Medicine("Brufen", "Anti inflammatory", 70.0, R.drawable.cough_syrup));
        medicines.add(new Medicine("Rigix", "Anti allergy", 65.0, R.drawable.cough_syrup));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MedicineAdapter(this, medicines, cart));

        btnOrder.setOnClickListener(v -> {
            if (cart.isEmpty()) {
                Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                cart.clear();
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }
}