package com.academics.hospitalmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {
    Context context;
    List<Medicine> items;
    List<Integer> quantities;

    public OrderSummaryAdapter(Context context, List<Medicine> items, List<Integer> quantities) {
        this.context = context;
        this.items = items;
        this.quantities = quantities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_order_summary_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicine med = items.get(position);
        int qty = quantities.get(position);

        holder.name.setText(med.name);
        holder.priceQty.setText("Rs, " + med.price + " x " + qty);

        if (med.imageDrawableId != 0) {
            holder.image.setImageResource(med.imageDrawableId);
        } else {
            holder.image.setImageResource(R.drawable.default_medicine);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, priceQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
            priceQty = itemView.findViewById(R.id.itemPriceQty);
            image = itemView.findViewById(R.id.itemImage);
        }
    }
}
