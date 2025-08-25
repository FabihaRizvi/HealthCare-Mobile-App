package com.academics.hospitalmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<Medicine> cartItems;
    Map<Medicine,Integer> cartMap;
    Runnable onCartUpdated;
    public CartAdapter(Context context, List<Medicine> cartItems, Map<Medicine, Integer> cartMap, Runnable onCartUpdated){
        this.context = context;
        this.cartItems = cartItems;
        this.cartMap = cartMap;
        this.onCartUpdated = onCartUpdated;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicine med = cartItems.get(position);

        holder.name.setText(med.name);
        holder.price.setText("RS. " + med.price);
        holder.quantityText.setText(String.valueOf(cartMap.get(med)));

        int resId = context.getResources().getIdentifier(med.imageResId, "drawable", context.getPackageName());
        if (resId != 0) {
            holder.image.setImageResource(resId);
        } else {
            holder.image.setImageResource(R.drawable.default_medicine);
        }

        holder.decreaseBtn.setOnClickListener(v -> {
            int qty = cartMap.get(med);
            if (qty > 1) {
                qty--;
                cartMap.put(med, qty);
                holder.quantityText.setText(String.valueOf(qty));
                onCartUpdated.run();
            }
        });

        holder.increaseBtn.setOnClickListener(v -> {
            int qty = cartMap.get(med) + 1;
            cartMap.put(med, qty);
            holder.quantityText.setText(String.valueOf(qty));
            onCartUpdated.run();
        });

        holder.deleteBtn.setOnClickListener(v -> {
            cartMap.remove(med);
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            onCartUpdated.run();
        });

    }

    @Override
    public int getItemCount(){
        return cartItems.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, price, quantityText;
        ImageView image,deleteBtn;
        Button decreaseBtn, increaseBtn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.cartMedicineName);
            price = itemView.findViewById(R.id.cartMedicinePrice);
            image = itemView.findViewById(R.id.cartMedicineImage);
            quantityText = itemView.findViewById(R.id.cartQuantityText);
            decreaseBtn = itemView.findViewById(R.id.btnCartDecrease);
            increaseBtn = itemView.findViewById(R.id.btnCartIncrease);
            deleteBtn = itemView.findViewById(R.id.btnDeleteCartItem);
        }
    }
}
