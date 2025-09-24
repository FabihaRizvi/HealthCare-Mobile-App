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
            ToastHelper.showToast(this, "OrderData NULL hai!");
            return;
        }

        customerName = (String) orderData.get("name");
        contactNumber = (String) orderData.get("contact");
        customerAddress = (String) orderData.get("address");
        deliveryTime = (String) orderData.get("deliveryTime");

        Object totalObj = orderData.get("totalAmount");
        if (totalObj != null) {
            totalAmount = Double.parseDouble(totalObj.toString());
        }

        tvSlipName.setText("Name: " + customerName);
        tvSlipContact.setText("Contact: " + contactNumber);
        tvSlipAddress.setText("Address: " + customerAddress);
        tvSlipTotal.setText("Total Amount: Rs. " + totalAmount);
        tvSlipDeliveryTime.setText("Delivery Time: " + deliveryTime);

        orderItems = (List<Medicine>) getIntent().getSerializableExtra("cartItems");
        List<Integer> qtyList = (List<Integer>) getIntent().getSerializableExtra("quantities");

        cartMap = new HashMap<>();
        if (orderItems != null && qtyList != null) {
            for (int i = 0; i < orderItems.size(); i++) {
                cartMap.put(orderItems.get(i), qtyList.get(i));
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
    }

    private void saveSlipAsImage() {
        slipLayout.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(slipLayout.getWidth(), slipLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        slipLayout.draw(canvas);

        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "OrderSlip_" + System.currentTimeMillis() + ".png");
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

            ToastHelper.showToast(this, "Slip saved in Gallery: " + file.getAbsolutePath());
        } catch (Exception e) {
            ToastHelper.showToast(this, "Error saving slip: " + e.getMessage());
        } finally {
            btnDownloadSlip.setVisibility(View.VISIBLE);
        }
    }
}
