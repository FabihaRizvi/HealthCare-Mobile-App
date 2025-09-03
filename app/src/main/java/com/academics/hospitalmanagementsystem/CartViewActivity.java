package com.academics.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartViewActivity extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    TextView backOption;
    Button placeOrderBtn;
    CartAdapter cartAdapter;
    Map<Medicine, Integer> cartMap;
    List<Medicine> cartItems;
    List<Integer> quantities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        placeOrderBtn = findViewById(R.id.placeOrderBtn);
        backOption = findViewById(R.id.backOption);

        backOption.setOnClickListener(v -> startActivity(new Intent(CartViewActivity.this, HomeActivity.class)));

        cartItems  = (List<Medicine>) getIntent().getSerializableExtra("cart");
        quantities = (List<Integer>) getIntent().getSerializableExtra("quantities");

        cartMap = new HashMap<>();
        if (cartItems != null && quantities != null){
            for(int i = 0; i < cartItems.size(); i++){
                cartMap.put(cartItems.get(i), quantities.get(i));
            }
        }

        cartItems = new ArrayList<>(cartMap.keySet());
        cartAdapter = new CartAdapter(this, cartItems, cartMap, () -> {
            cartAdapter.notifyDataSetChanged();
        }, true);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        placeOrderBtn.setOnClickListener(v -> {
            if (cartMap.isEmpty()){
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
            }else {
                ArrayList<Medicine> items = new ArrayList<>();
                ArrayList<Integer> qtyList = new ArrayList<>();
                for (Map.Entry<Medicine, Integer> entry : cartMap.entrySet()) {
                    items.add(entry.getKey());
                    qtyList.add(entry.getValue());
                }
                Intent intent = new Intent(CartViewActivity.this, OrderFormActivity.class);
                intent.putExtra("cart", items);
                intent.putExtra("quantities", qtyList);
                startActivity(intent);
            }
        });
    }
}