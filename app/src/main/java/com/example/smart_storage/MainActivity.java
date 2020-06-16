package com.example.smart_storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    static int active = 0; //0 = All - 1 = Pantry - 2 = Freezer - 3 = Fridge
    static String[] item = new String[10];
    static String[] date = new String[10];
    static String[] storageType = new String[10]; //arrays for user inputted items/dates;
    static int itemAmount = 0;

    TextView theDate;
    Button pantryButton, freezerButton, fridgeButton, addFoodButton, calendarButton;
    RecyclerView recyclerView;
    View constraintLayout;
    int toggle = 1; //has the same button been pressed three times in a row? if so, toggle background colour to that button's colour


    public static void setItem(Editable addItem) {
        item[itemAmount] = String.valueOf(addItem);
    }

    public static void setDate(String addDate) {
        date[itemAmount] = addDate;
    }

    public static void setStorageType(String addStorageType) {
        storageType[itemAmount] = addStorageType;
        itemAmount++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theDate = findViewById(R.id.date);
        constraintLayout = findViewById(R.id.constraint_Layout);
        pantryButton = findViewById(R.id.pantry_Button);
        freezerButton = findViewById(R.id.freezer_Button);
        fridgeButton = findViewById(R.id.fridge_Button);
        addFoodButton = findViewById(R.id.add_item_button);
        calendarButton = findViewById(R.id.calendar_button);

        recyclerView = findViewById(R.id.myRecyclerView);
        for (int i=0; i<10;i++){
            item[i]= "";
            date[i] = "";
            storageType[i] = "";
        }

        updateRecycler();

        pantryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantry();
            }
        });

        freezerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //set back to all foods list
                freezer();
            }
        });

        fridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //set back to all foods list
                fridge();
            }
        });



        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFoodActivity();
            }
        });
        loadArray( "Item",this);
        loadArray( "date", this);
        loadArray("storageType",this);
    }





    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (itemAmount != 0) {
            if (active == 1) pantry();
            else if (active == 2) freezer();
            else if (active == 3) fridge();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveArray(item, "Item",this);
        saveArray(date, "date", this);
        saveArray(storageType, "storeType", this);
    }

    public void pantry() {
        if (active == 1 && toggle == 0) { //set back to all foods list
            active = 0;
            toggle = 1;
            constraintLayout.setBackgroundColor(Color.parseColor("#dcffd6"));
            pantryButton.setTypeface(null, Typeface.NORMAL);
            updateRecycler();
        } else { //set to pantry food list
            active = 1;
            toggle = 0;
            constraintLayout.setBackgroundColor(Color.parseColor("#bf9573"));
            pantryButton.setTypeface(null, Typeface.BOLD_ITALIC);
            freezerButton.setTypeface(null, Typeface.NORMAL);
            fridgeButton.setTypeface(null, Typeface.NORMAL);
            updateRecycler();
        }
    }

    public void freezer() {
        if (active == 2 && toggle == 0) {
            active = 0;
            toggle = 1;
            constraintLayout.setBackgroundColor(Color.parseColor("#dcffd6"));
            freezerButton.setTypeface(null, Typeface.NORMAL);
            updateRecycler();
        } else { //set to freezer food list
            active = 2;
            toggle = 0;
            constraintLayout.setBackgroundColor(Color.parseColor("#304bff"));
            freezerButton.setTypeface(null, Typeface.BOLD_ITALIC);
            pantryButton.setTypeface(null, Typeface.NORMAL);
            fridgeButton.setTypeface(null, Typeface.NORMAL);
            updateRecycler();
        }
    }

    public void fridge() {
        if (active == 3 && toggle == 0) {
            active = 0;
            toggle = 1;
            constraintLayout.setBackgroundColor(Color.parseColor("#dcffd6"));
            fridgeButton.setTypeface(null, Typeface.NORMAL);
            updateRecycler();
        } else { //set to fridge food list
            active = 3;
            toggle = 0;
            constraintLayout.setBackgroundColor(Color.parseColor("#abb6ff"));
            fridgeButton.setTypeface(null, Typeface.BOLD_ITALIC);
            pantryButton.setTypeface(null, Typeface.NORMAL);
            freezerButton.setTypeface(null, Typeface.NORMAL);
            updateRecycler();
        }
    }

    private void updateRecycler() {
        MyAdapter myAdapter = new MyAdapter(this, item, date, storageType);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void openAddFoodActivity() {
        Intent intent = new Intent(this, add_food_activity.class);
        startActivity(intent);
    }

    public int getActive() {
        return active;
    }

    public boolean saveArray(String[] array, String Name, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("prefrencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Name + "_size", array.length);
        for (int i = 0; i < array.length; i++)
            editor.putString(Name + " " + i, array[i]);
        return editor.commit();
    }

    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("prefrencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String[] array = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = prefs.getString(arrayName + " " + i, null);
        return array;
    }
}
