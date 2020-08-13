package com.example.android.inventory.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.inventory.model.Inventory;

import java.util.List;
import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
    public interface InventoryDao {

        @Insert(onConflict = IGNORE)
        long insertInventory(Inventory inventory);

        @Update
        int updateInventory(Inventory inventory);

        @Delete
        void deleteInventory(Inventory inventory);

        @Query("SELECT * FROM items_table WHERE id = :id")
        Inventory findInventory(int id);

        @Query("DELETE FROM items_table")
        void deleteAllInventories();

        @Query("SELECT * FROM items_table")
        LiveData<List<Inventory>>findAllInventories();
}
