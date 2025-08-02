package com.academics.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyMedicineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnOrder;
    ImageView cartIcon;
    TextView cartCount;
    List<Medicine> medicines;
    HashMap<Medicine, Integer> cartMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        recyclerView = findViewById(R.id.medicineRecyclerView);
        btnOrder = findViewById(R.id.btnOrder);
        cartIcon = findViewById(R.id.cartIcon);
        cartCount = findViewById(R.id.cartCount);

        medicines = new ArrayList<>();
        cartMap = new HashMap<>();
//
//        medicines.add(new Medicine("Panadol", "Pain reliever and fever reducer", 50.0, R.drawable.panadol_tab));
//        medicines.add(new Medicine("Brufen", "Anti inflammatory", 70.0, R.drawable.brufen_tab));
//        medicines.add(new Medicine("Rigix", "Anti allergy", 65.0, R.drawable.rigix_tab));
//
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MedicineAdapter(this, medicines, cartMap, this::updateCartBadge));

        btnOrder.setOnClickListener(v -> {
            if (cartMap.isEmpty()) {
                Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, OrderFormActivity.class);
                intent.putExtra("quantities", new ArrayList<>(cartMap.values()));
                intent.putExtra("cart", new ArrayList<>(cartMap.keySet()));
                startActivity(intent);
            }
        });

        cartIcon.setOnClickListener(v -> {
            if (cartMap.isEmpty()) {
                Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(BuyMedicineActivity.this, CartViewActivity.class);
                intent.putExtra("cart", new ArrayList<>(cartMap.keySet()));
                intent.putExtra("quantities", new ArrayList<>(cartMap.values()));
                startActivity(intent);
            }
        });

        updateCartBadge();
    }

        private void updateCartBadge() {
            int totalItems = 0;
            for (int qty : cartMap.values()) {
                totalItems += qty;
            }
            if (totalItems > 0) {
                cartCount.setVisibility(View.VISIBLE);
                cartCount.setText(String.valueOf(totalItems));
            } else {
                cartCount.setVisibility(View.GONE);
            }
        }
    }
}