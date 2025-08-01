package com.academics.hospitalmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    Context context;
    List<Medicine> medicineList;
    List<Medicine> cartList;

    public MedicineAdapter(Context context, List<Medicine> medicineList, List<Medicine> cartList) {
        this.context = context;
        this.medicineList = medicineList;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_medicine_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ViewHolder holder, int position) {
        Medicine med = medicineList.get(position);
        holder.name.setText(med.name);
        holder.price.setText("Rs. " + med.price);
        holder.image.setImageResource(med.imageResId);

        holder.addBtn.setOnClickListener(v -> {
            cartList.add(med);
            Toast.makeText(context, med.name + " added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView image;
        Button addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medicineName);
            price = itemView.findViewById(R.id.medicinePrice);
            image = itemView.findViewById(R.id.medicineImage);
            addBtn = itemView.findViewById(R.id.addToCartBtn);
        }
    }
}
