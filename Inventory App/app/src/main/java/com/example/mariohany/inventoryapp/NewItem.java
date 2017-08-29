package com.example.mariohany.inventoryapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_IMAGE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_NAME;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_PRICE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.CONTENT_URI;
import static com.example.mariohany.inventoryapp.data.ItemProvider.LOG_TAG;

public class NewItem extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 0;
    private EditText nameEditText;
    private EditText quantityEditText;
    private EditText priceEditText;
    private String imagePath;
    private Uri mUri;
    private Button add;
    private Button photoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        add = (Button) findViewById(R.id.save);
        photoBtn = (Button) findViewById(R.id.photo);

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageSelector();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();
                String quantityString = quantityEditText.getText().toString();
                String priceString = priceEditText.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(quantityString) || TextUtils.isEmpty(priceString) || TextUtils.isEmpty(imagePath)) {
                    Toast.makeText(getApplicationContext(), "please fill all spaces !", Toast.LENGTH_SHORT).show();
                } else {
                    Long quantity = Long.parseLong(quantityString);
                    float price = Float.valueOf(priceString);
                    addNewItem(name, quantity, price);
                }
            }
        });
    }
    public void openImageSelector(){
        Intent intent;

        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }

        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void addNewItem(String name, Long quantity, Float price){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, name);
        values.put(COLUMN_ITEM_QUANTITY, quantity);
        values.put(COLUMN_ITEM_PRICE, price);
        values.put(COLUMN_ITEM_IMAGE, imagePath);
        getContentResolver().insert(CONTENT_URI, values);
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                mUri = resultData.getData();
                Log.i(LOG_TAG, "Uri: " + mUri.toString());
                imagePath = mUri.toString();
            }
        }
    }

}
