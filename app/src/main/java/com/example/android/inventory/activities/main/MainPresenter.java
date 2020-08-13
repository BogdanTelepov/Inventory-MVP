package com.example.android.inventory.activities.main;

import com.example.android.inventory.model.Inventory;
import com.example.android.inventory.data.InventoryDao;


public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View view;
    private final InventoryDao inventoryDao;

    public MainPresenter(MainContract.View view, InventoryDao inventoryDao) {
        this.view = view;
        this.view.setPresenter(this);
        this.inventoryDao = inventoryDao;
    }

    @Override
    public void addItem() {
        view.showItem();
    }

    @Override
    public void initializeItems() {
        inventoryDao.findAllInventories().observeForever(inventories -> {
            view.setItems(inventories);
            if (inventories == null || inventories.size() < 1) {
                view.showMessage();
            }
        });
    }

    @Override
    public void openEditActivity(Inventory inventory) {
        view.showEditActivity(inventory.getId());
    }


    @Override
    public void deleteAllItems() {
        inventoryDao.deleteAllInventories();
    }
}