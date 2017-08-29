package com.example.mariohany.inventoryapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_IMAGE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_NAME;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_PRICE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY;
import static com.example.mariohany.inventoryapp.data.ItemProvider.LOG_TAG;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PICK_IMAGE_REQUEST = 0;
    private Uri currentItemUri;
    private EditText nameEditText;
    private EditText priceEditText;
    private TextView quantityTextView;
    private Button save;
    private Button orderMore;
    private Button delete;
    private Button quantityPlus;
    private Button quantityMinus;
    private String imagePath;
    private ImageView image;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        quantityTextView = (TextView) findViewById(R.id.quantityTextView);
        save = (Button) findViewById(R.id.save);
        orderMore = (Button) findViewById(R.id.order_more);
        delete = (Button) findViewById(R.id.delete);
        image = (ImageView) findViewById(R.id.image);
        quantityPlus = (Button) findViewById(R.id.quantity_plus);
        quantityMinus = (Button) findViewById(R.id.quantity_minus);
        Intent intent = getIntent();
        currentItemUri = intent.getData();
        setTitle("Details Screen");
        getLoaderManager().initLoader(0,null,this);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageSelector();
            }
        });

        quantityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long quantity = Long.parseLong(quantityTextView.getText().toString().trim());
                ContentValues values = new ContentValues();
                values.put(COLUMN_ITEM_QUANTITY, quantity+1);
                getContentResolver().update(currentItemUri, values, null, null);

            }
        });

        quantityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long quantity = Long.parseLong(quantityTextView.getText().toString().trim());
                if (quantity >=1){
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_ITEM_QUANTITY, quantity-1);
                    getContentResolver().update(currentItemUri, values, null, null);
                }else{
                    Toast.makeText(DetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();
                Long quantity = Long.parseLong(quantityTextView.getText().toString().trim());
                Float price = Float.parseFloat(priceEditText.getText().toString().trim());

                ContentValues values = new ContentValues();
                values.put(COLUMN_ITEM_NAME, name);
                values.put(COLUMN_ITEM_QUANTITY, quantity);
                values.put(COLUMN_ITEM_PRICE, price);
                Bitmap icLanucher = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                if(!equals(bitmap,icLanucher) && imagePath != null) {
                    values.put(COLUMN_ITEM_IMAGE, imagePath);
                }

                int rowsAffected = getContentResolver().update(currentItemUri, values, null, null);

                if (rowsAffected == 0) {
                    Toast.makeText(getApplicationContext(), "Failed to update Item", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Item Updated successfully", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            public boolean equals(Bitmap bitmap1, Bitmap bitmap2) {
                ByteBuffer buffer1 = ByteBuffer.allocate(bitmap1.getHeight() * bitmap1.getRowBytes());
                bitmap1.copyPixelsToBuffer(buffer1);

                ByteBuffer buffer2 = ByteBuffer.allocate(bitmap2.getHeight() * bitmap2.getRowBytes());
                bitmap2.copyPixelsToBuffer(buffer2);

                return Arrays.equals(buffer1.array(), buffer2.array());
            }
        });

        orderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: Udacity@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Order More");
                intent.putExtra(Intent.EXTRA_TEXT, "hi we need more from this item : " + nameEditText.getText().toString().trim());
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                int rowsDeleted = getContentResolver().delete(currentItemUri, null, null);
                                if (rowsDeleted == 0) {
                                    Toast.makeText(getApplicationContext(), "Failed !!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(DetailsActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder ab = new AlertDialog.Builder(DetailsActivity.this);
                ab.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
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

    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                mUri = resultData.getData();
                Log.i(LOG_TAG, "Uri: " + mUri.toString());
                imagePath = mUri.toString();
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                ItemEntry._ID,
                COLUMN_ITEM_NAME,
                COLUMN_ITEM_QUANTITY,
                COLUMN_ITEM_PRICE,
                COLUMN_ITEM_IMAGE };

        return new CursorLoader(this,
                currentItemUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null){
            return ;
        }
        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_ITEM_NAME);
            int priceColumnIndex = cursor.getColumnIndex(COLUMN_ITEM_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(COLUMN_ITEM_QUANTITY);
            int imageColumnIndex = cursor.getColumnIndex(COLUMN_ITEM_IMAGE);

            String name = cursor.getString(nameColumnIndex);
            Float price = cursor.getFloat(priceColumnIndex);
            Long quantity = cursor.getLong(quantityColumnIndex);
            String imageString = cursor.getString(imageColumnIndex);

            nameEditText.setText(name);
            priceEditText.setText(Float.toString(price));
            quantityTextView.setText(Long.toString(quantity));
            if (imageString != null){
                image.setImageURI(Uri.parse(imageString));
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        nameEditText.setText("");
        quantityTextView.setText("");
        priceEditText.setText("");
        image.setImageResource(R.mipmap.ic_launcher);
    }
}
