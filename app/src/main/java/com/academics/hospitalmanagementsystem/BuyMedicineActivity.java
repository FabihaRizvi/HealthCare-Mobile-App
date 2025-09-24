package com.academics.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyMedicineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView cartIcon;
    TextView cartCount, backOption;
    List<Medicine> medicines;
    HashMap<Medicine, Integer> cartMap;
    MedicineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        recyclerView = findViewById(R.id.medicineRecyclerView);
        cartIcon = findViewById(R.id.cartIcon);
        cartCount = findViewById(R.id.cartCount);
        backOption = findViewById(R.id.backOption);

        backOption.setOnClickListener(v -> startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class)));

        medicines = new ArrayList<>();
        cartMap = new HashMap<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MedicineAdapter(this, medicines, cartMap, this::updateCartBadge);
        recyclerView.setAdapter(adapter);

        fetchMedicineFromFirebase();

        cartIcon.setOnClickListener(v -> {
            if (cartMap.isEmpty()) {
                ToastHelper.showToast(BuyMedicineActivity.this, "Your cart is empty");
            } else {
                Intent intent = new Intent(BuyMedicineActivity.this, CartViewActivity.class);
                intent.putExtra("cart", new ArrayList<>(cartMap.keySet()));
                intent.putExtra("quantities", new ArrayList<>(cartMap.values()));
                startActivity(intent);
            }
        });

        updateCartBadge();
    }

    private void fetchMedicineFromFirebase() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Medicines");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                medicines.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Medicine med = snap.getValue(Medicine.class);

                    if (med != null) {
                        int resId = getResources().getIdentifier(med.imageResId, "drawable", getPackageName());
                        med.imageDrawableId = resId != 0 ? resId : R.drawable.default_medicine;
                        med.quantity = 1;
                        medicines.add(med);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ToastHelper.showToast(BuyMedicineActivity.this, "Failed to load medicines: " + databaseError.getMessage());
            }
        });
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