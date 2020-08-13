package com.example.android.inventory.activities.main;

import com.example.android.inventory.views.BaseView;
import com.example.android.inventory.model.Inventory;

import java.util.List;

public interface MainContract {

    interface Presenter {

        void addItem();

        void initializeItems();

        void openEditActivity(Inventory inventory);

        void deleteAllItems();
    }

    interface View extends BaseView<Presenter> {

        void showItem();

        void setItems(List<Inventory> inventories);

        void showEditActivity(int id);

        void showMessage();

        void showAlertDialog();
    }

    interface OnItemClickListener {

        void onItemClick(Inventory inventory);



    }
}
