package com.example.mariohany.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Mario Hany on 2017-07-19.
 */

public class HabitContract {
    public static abstract class HabitEntry implements BaseColumns{
        public static final String TABLE_NAME = "habit";
        public static final String ID = "id";
        public static final String COLUMN_HABIT_NAME = "Habit";
        public static final String COLUMN_HABIT_TIME = "Time";
    }
}
