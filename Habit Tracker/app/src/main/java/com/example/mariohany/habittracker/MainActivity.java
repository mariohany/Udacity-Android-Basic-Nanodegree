package com.example.mariohany.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mariohany.habittracker.data.HabitContract.HabitEntry;
import com.example.mariohany.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {
    public EditText nameEditText;
    public EditText timeEditText;
    public TextView displayView;
    public Button add;
    public HabitDbHelper mDbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new HabitDbHelper(getApplicationContext());
        nameEditText = (EditText) findViewById(R.id.habit_name);
        timeEditText = (EditText) findViewById(R.id.habit_time);
        displayView = (TextView) findViewById(R.id.text_view);
        add = (Button) findViewById(R.id.add_button);
        //display table data when start the app
        displayResult();
        //insert data and show them when the buttom is pressed
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                insertHabit();
                displayResult();
            }
        });

    }
    //this function to insert new raw in the database
    private void insertHabit(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String name = nameEditText.getText().toString().trim();
        int time = Integer.parseInt(timeEditText.getText().toString().trim());

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_TIME, time);
        db.insert(HabitEntry.TABLE_NAME, null, values);
    }
    //this function to display all the data from the database
    private void displayResult(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = read();
        displayView.setText("");
        try {
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int timeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TIME);


            while(cursor.moveToNext()){
                String currentName = cursor.getString(nameColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);
                displayView.append("Habit : " + currentName + "\n" + "Time : " + currentTime + "\n\n");
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    public Cursor read() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_TIME};

        // Perform a query to return a cursor with the desired data.
        Cursor cursor = db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }
}
