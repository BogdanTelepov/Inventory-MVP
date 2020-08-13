package com.example.android.inventory.activities.edit;

import com.example.android.inventory.views.BaseView;
import com.example.android.inventory.model.Inventory;

public interface EditContract {
    interface Presenter {
        void saveItem(Inventory inventory);

        void deleteItem(int id);

        void getItem(int id);

        void update(Inventory inventory);
    }

    interface View extends BaseView<Presenter> {

        void close();

        void initializeItem(Inventory inventory);
    }
}
