package com.example.android.inventory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        Inventory currentInventory = inventoryList.get(position);
        holder.inventory = currentInventory;
        holder.txtTitle.setText(currentInventory.getTitle());
        holder.txtPrice.setText(String.valueOf(currentInventory.getPrice()));
        holder.txtDescription.setText(currentInventory.getDescription());
        holder.txtQuantity.setText(currentInventory.getQuantity());
//        Glide.with(holder.imageView.getContext())
//                .load(currentInventory.getImage())
//                .into(holder.imageView);

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
        private final TextView txtTitle;
        private final TextView txtPrice;
        private final TextView txtDescription;
        private final TextView txtQuantity;
        private ImageView imageView;
        public Inventory inventory;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            txtTitle = view.findViewById(R.id.txt_item_title);
            txtPrice = view.findViewById(R.id.txt_item_price);
            txtDescription = view.findViewById(R.id.txt_item_description);
            txtQuantity = view.findViewById(R.id.txt_item_quantity);
            imageView = view.findViewById(R.id.image_item);

        }
    }
}