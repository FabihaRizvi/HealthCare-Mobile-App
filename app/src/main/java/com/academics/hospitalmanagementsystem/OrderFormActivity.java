package com.academics.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFormActivity extends AppCompatActivity {
    EditText etName, etContact, etAddress;
    TextView tvTotalAmount,backOption;
    Button btnConfirmOrder;
    RecyclerView orderSummaryRecycler;

    List<Medicine> orderItems;
    List<Integer> quantities;
    OrderSummaryAdapter summaryAdapter;

    double subTotal = 0;
    final double deliveryFee = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        etName = findViewById(R.id.etName);
        etContact = findViewById(R.id.etContact);
        etAddress = findViewById(R.id.etAddress);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnConfirmOrder = findViewById(R.id.btnConfirmOrder);
        backOption = findViewById(R.id.backOption);
        orderSummaryRecycler = findViewById(R.id.orderSummaryRecycler);

        backOption.setOnClickListener(v -> finish());

        orderItems = (List<Medicine>) getIntent().getSerializableExtra("cart");
        quantities = (List<Integer>) getIntent().getSerializableExtra("quantities");

        if (orderItems == null) orderItems = new ArrayList<>();
        if (quantities == null) quantities = new ArrayList<>();

        summaryAdapter = new OrderSummaryAdapter(this, orderItems, quantities);
        orderSummaryRecycler.setLayoutManager(new LinearLayoutManager(this));
        orderSummaryRecycler.setAdapter(summaryAdapter);


        for (int i = 0; i < orderItems.size(); i++) {
            Medicine med = orderItems.get(i);
            int qty = quantities.get(i);
            subTotal += med.price * qty;
        }

        double totalAmount = subTotal + deliveryFee;
        tvTotalAmount.setText("Total: Rs. " + subTotal + " + " + deliveryFee + "(Delivery) Rs. " + totalAmount);

        btnConfirmOrder.setOnClickListener(v -> {
            String customerName = etName.getText().toString().trim();
            String contactNumber = etContact.getText().toString().trim();
            String customerAddress = etAddress.getText().toString().trim();

            if (customerName.isEmpty() || contactNumber.isEmpty() || customerAddress.isEmpty()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
                return;
            }

            DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("OrderDetails");
            String orderId = orderRef.push().getKey();

            Map<String, Object> orderData = new HashMap<>();
            orderData.put("name", customerName);
            orderData.put("contact", contactNumber);
            orderData.put("address", customerAddress);
            orderData.put("deliveryFee", deliveryFee);
            orderData.put("subTotal", subTotal);
            orderData.put("totalAmount", totalAmount);
            orderData.put("deliveryTime", "Within 24 hours");

            List<Map<String, Object>> medicineList = new ArrayList<>();
            for (int i = 0; i < orderItems.size(); i++) {
                Medicine med = orderItems.get(i);
                int qty = quantities.get(i);

                Map<String, Object> medData = new HashMap<>();
                medData.put("name", med.name);
                medData.put("price", med.price);
                medData.put("quantity", qty);
                medicineList.add(medData);
            }
            orderData.put("medicines", medicineList);

            orderRef.child(orderId).setValue(orderData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(OrderFormActivity.this, "Order placed successfully!", Toast.LENGTH_LONG).show();
                        Intent slipIntent = new Intent(OrderFormActivity.this, OrderSlipActivity.class);
                        slipIntent.putExtra("orderId", orderId);
                        slipIntent.putExtra("orderData", new HashMap<>(orderData));
                        startActivity(slipIntent);
//                        finish();
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(OrderFormActivity.this, "Failed:" + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
        });
    }
}