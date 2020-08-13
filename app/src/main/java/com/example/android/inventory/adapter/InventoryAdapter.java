package com.example.android.inventory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.inventory.model.Inventory;
import com.example.android.inventory.R;
import com.example.android.inventory.activities.main.MainContract;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    private List<Inventory> inventoryList;
    private MainContract.OnItemClickListener onItemClickListener;

    public InventoryAdapter(MainContract.OnItemClickListener onItemClickListener) {
        this.inventoryList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.inventory = inventoryList.get(position);
        holder.nameTextView.setText(inventoryList.get(position).getTitle());
        holder.priceTextView.setText(String.valueOf(inventoryList.get(position).getPrice()));
        holder.supplierTextView.setText(inventoryList.get(position).getDescription());
        holder.quantityTextView.setText(inventoryList.get(position).getQuantity());

        holder.view.setOnClickListener(v -> onItemClickListener.onItemClick(holder.inventory));
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    public void setValues(List<Inventory> values) {
        inventoryList = values;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        private final TextView nameTextView;
        private final TextView priceTextView;
        private final TextView supplierTextView;
        private final TextView quantityTextView;
        private Inventory inventory;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            nameTextView = view.findViewById(R.id.txt_item_title);
            priceTextView = view.findViewById(R.id.txt_item_price);
            supplierTextView = view.findViewById(R.id.txt_item_description);
            quantityTextView = view.findViewById(R.id.txt_item_quantity);

        }
    }
}