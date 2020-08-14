package com.example.android.inventory.activities.edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.android.inventory.model.Inventory;
import com.example.android.inventory.R;
import com.example.android.inventory.data.InventoryDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;


public class EditActivity extends AppCompatActivity implements EditContract.View {


    final int CAMERA_REQUEST = 51;

    private EditContract.Presenter presenter;
    private Bitmap bitmap;
    private EditText editText_title;
    private EditText editText_price;
    private EditText editText_description;
    private EditText editText_quantity;
    private ImageView imageView;
    private ImageButton imageButton;
    private FloatingActionButton floatingActionButton;

    private Inventory inventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inventory = new Inventory();
        setupViews();
        InventoryDatabase db = InventoryDatabase.getInstance(getApplication());
        presenter = new EditPresenter(this, db.inventoryDao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        setTitle("Add Item");
        super.onStart();
        presenter.getItem(inventory.getId());

    }

    private void setupViews() {
        editText_title = findViewById(R.id.edit_text_name);
        editText_price = findViewById(R.id.edit_text_price);
        editText_description = findViewById(R.id.edit_text_supplier);
        editText_quantity = findViewById(R.id.edit_text_quantity);
        imageView = findViewById(R.id.image_item);
        imageButton = findViewById(R.id.button_take_photo);
        floatingActionButton = findViewById(R.id.floating_action_button_saveItem);
        floatingActionButton.setOnClickListener(v -> {
            inventory.setTitle(editText_title.getText().toString());
            inventory.setPrice(editText_price.getText().toString());
            inventory.setDescription(editText_description.getText().toString());
            inventory.setQuantity(editText_quantity.getText().toString());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageInByte = stream.toByteArray();
            inventory.setImage(imageInByte);
            presenter.saveItem(inventory);

            if(inventory.getTitle()!=null || inventory.getDescription()!=null || inventory.getPrice()!=null || inventory.getQuantity()!=null){
                presenter.update(inventory);
            }
        });
    }

    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void close() {
        finish();
    }

    @Override
    public void initializeItem(Inventory inventory) {
        this.inventory = inventory;
        editText_title.setText(inventory.getTitle());
        editText_price.setText(String.valueOf(inventory.getPrice()));
        editText_description.setText(inventory.getDescription());
        editText_quantity.setText(inventory.getQuantity());
        Bitmap bmp = BitmapFactory.decodeByteArray(inventory.getImage(), 0, inventory.getImage().length);
        imageView.setImageBitmap(bmp);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST) {
            if (data != null) {
                bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }

    }


}