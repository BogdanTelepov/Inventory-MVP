package com.example.android.inventory.activities.main;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.inventory.adapter.InventoryAdapter;
import com.example.android.inventory.model.Inventory;
import com.example.android.inventory.R;
import com.example.android.inventory.data.InventoryDatabase;
import com.example.android.inventory.activities.edit.EditActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, MainContract.OnItemClickListener {

    public static final String EXTRA_ID = "inventory_id";
    private MainContract.Presenter presenter;
    private InventoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(v -> presenter.addItem());


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InventoryAdapter(this);
        recyclerView.setAdapter(adapter);

        InventoryDatabase db = InventoryDatabase.getInstance(getApplication());
        presenter = new MainPresenter(this, db.inventoryDao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initializeItems();
    }

    @Override
    public void showItem() {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivity(intent);
    }

    @Override
    public void setItems(List<Inventory> inventories) {

        adapter.setValues(inventories);
    }

    @Override
    public void showEditActivity(int id) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra(EXTRA_ID, id);
        startActivity(intent);
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(Inventory inventory) {
        presenter.openEditActivity(inventory);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                showAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Would you like delete all items?");
        alert.setPositiveButton("Yes", (dialog, i) -> presenter.deleteAllItems());
        alert.setNegativeButton("No", (dialog, which) -> {

        });
        alert.create().show();
    }
}