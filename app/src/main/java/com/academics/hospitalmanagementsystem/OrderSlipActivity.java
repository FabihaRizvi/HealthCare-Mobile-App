package com.academics.hospitalmanagementsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSlipActivity extends AppCompatActivity {
    TextView tvSlipName, tvSlipContact, tvSlipAddress, tvSlipTotal, tvSlipDeliveryTime;
    RecyclerView rvSlipMedicines;
    Button btnDownloadSlip;
    ConstraintLayout slipLayout;

    List<Medicine> orderItems;
    Map<Medicine, Integer> cartMap;
    double totalAmount;
    String customerName, customerAddress, contactNumber, deliveryTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_slip);

        tvSlipName = findViewById(R.id.tvSlipName);
        tvSlipContact = findViewById(R.id.tvSlipContact);
        tvSlipAddress = findViewById(R.id.tvSlipAddress);
        tvSlipTotal = findViewById(R.id.tvSlipTotal);
        tvSlipDeliveryTime = findViewById(R.id.tvSlipDeliveryTime);
        rvSlipMedicines = findViewById(R.id.rvSlipMedicines);
        btnDownloadSlip = findViewById(R.id.btnDownloadSlip);
        slipLayout = findViewById(R.id.slipRoot);

        HashMap<String, Object> orderData = (HashMap<String, Object>) getIntent().getSerializableExtra("orderData");
        if (orderData == null) {
            Toast.makeText(this, "OrderData NULL hai!", Toast.LENGTH_LONG).show();
            return;
        }
        if (orderData != null){
            customerName = (String) orderData.get("name");
            contactNumber = (String) orderData.get("contact");
            customerAddress = (String) orderData.get("address");
            deliveryTime = (String) orderData.get("deliveryTime");

            Object totalObj = orderData.get("totalAmount");
            if (totalObj != null) {
                totalAmount = Double.parseDouble(totalObj.toString());
            }

            tvSlipName.setText("Name: "+ customerName);
            tvSlipContact.setText("Contact: "+ contactNumber);
            tvSlipAddress.setText("Address: "+ customerAddress);
            tvSlipTotal.setText("Total Amount: Rs. " + totalAmount);
            tvSlipDeliveryTime.setText("Delivery Time: " + deliveryTime);
        }

        orderItems = new ArrayList<>();
        cartMap = new HashMap<>();

        Object medsObj = orderData.get("medicines");
        if (medsObj instanceof List) {
            List<?> rawList = (List<?>) medsObj;
            for (Object obj : rawList) {
                if (obj instanceof HashMap) {
                    HashMap<String, Object> medData = (HashMap<String, Object>) obj;

                    String medName = (String) medData.get("name");
                    double price = 0;
                    Object priceObj = medData.get("price");
                    if (priceObj != null) {
                        price = Double.parseDouble(priceObj.toString());
                    }

                    int qty = 0;
                    Object qtyObj = medData.get("quantity");
                    if (qtyObj != null) {
                        qty = Integer.parseInt(qtyObj.toString());
                    }

                    Medicine med = new Medicine();
                    med.name = medName;
                    med.price = price;
                    med.imageResId = "default_medicine";
                    med.imageDrawableId = R.drawable.default_medicine;
                    orderItems.add(med);
                    cartMap.put(med, qty);
                }
            }
        }

        CartAdapter adapter = new CartAdapter(this, orderItems, cartMap, () -> {}, true);
        rvSlipMedicines.setLayoutManager(new LinearLayoutManager(this));
        rvSlipMedicines.setAdapter(adapter);

        btnDownloadSlip.setOnClickListener(v -> {
            btnDownloadSlip.setVisibility(View.GONE);
            saveSlipAsImage();
            startActivity(new Intent(OrderSlipActivity.this, HomeActivity.class));
            finish();
        });
        adapter.notifyDataSetChanged();

    }

    private void saveSlipAsImage(){
        slipLayout.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(slipLayout.getWidth(), slipLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        slipLayout.draw(canvas);

        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "OrderSlip_" + System.currentTimeMillis() + ".png");
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100, fos);
            fos.flush();
            fos.close();

            Toast.makeText(this, "Slip saved in Gallery: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            Intent homeIntent = new Intent(OrderSlipActivity.this, HomeActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            finish();
        }catch (Exception e){
            Toast.makeText(this, "Error saving slip: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            btnDownloadSlip.setVisibility(View.VISIBLE);
        }
    }
}