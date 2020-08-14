package com.example.android.inventory.activities.edit;

import com.example.android.inventory.model.Inventory;
import com.example.android.inventory.data.InventoryDao;

public class EditPresenter implements EditContract.Presenter {
    private final EditContract.View view;
    private final InventoryDao inventoryDao;

    public EditPresenter(EditContract.View mMainView, InventoryDao inventoryDao) {
        this.view = mMainView;
        this.view.setPresenter(this);
        this.inventoryDao = inventoryDao;
    }

    @Override
    public void saveItem(Inventory inventory) {
        long id = this.inventoryDao.insertInventory(inventory);
        view.close();
    }

    @Override
    public void update(Inventory inventory) {
        long id = this.inventoryDao.updateInventory(inventory);
        view.close();
    }

    @Override
    public void deleteItem(int inventoryId){
        Inventory inventory = inventoryDao.findInventory(inventoryId);
         inventoryDao.deleteInventory(inventory);
    }

    @Override
    public void getItem(int id) {
        Inventory inventory = inventoryDao.findInventory(id);
        if (inventory != null) {
            view.initializeItem(inventory);
        }
    }
}