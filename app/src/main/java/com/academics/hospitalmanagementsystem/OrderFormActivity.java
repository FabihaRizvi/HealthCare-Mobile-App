package com.academics.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class OrderFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("OrderDetails");
        String orderId = orderRef.push().getKey();

        Map<String, Object> orderData = new HashMap<>();
        orderData.put("name", customerName);
        orderData.put("contact", contactNumber);
        orderData.put("address", customerAddress);
        orderData.put("shipping fee", shippingFee);
        orderData.put("delivery time", "Within 24 hours");
        orderData.put("total amount", calculatedPrice);
        orderData.put("medicines", cartList);

        orderRef.child(orderId).setValue(orderData)
                .addOnSuccessListener(aVoid ->
                        Toast.makeText(OrderFormActivity.this, "Order placed successfully!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(OrderFormActivity.this, "Failed to place order. Please try again."+ e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}