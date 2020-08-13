package com.example.android.inventory.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table")
public class Inventory {

    @PrimaryKey(autoGenerate = true)

    private int id;
    private String title;

    private String quantity;

    private String price;

    private String description;

    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Inventory(String title, String quantity, String price, String description) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.description = description;

    }


    public Inventory() {

    }

    public Inventory(String title, String quantity, String price, String description, byte[] image) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
    }
}
