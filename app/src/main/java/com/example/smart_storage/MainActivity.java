package com.example.smart_storage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    static int active = 0; //0 = All - 1 = Pantry - 2 = Freezer - 3 = Fridge
    Button pantryButton, freezerButton, fridgeButton, addFoodButton;
    RecyclerView recyclerView;
    View constraintLayout;
    String[] item = {"test1,", "test2,", "test3,", "test4,", "test5"};
    String[] date = {"test1,", "test2,", "test3,", "test4,", "test5"};
    String[] storageType = {"1", "3", "2", "2", "1"}; //arrays for user inputted items/dates;
    int toggle = 1; //has the same button been pressed three times in a row? if so, toggle background colour to that button's colour

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint_Layout);
        pantryButton = findViewById(R.id.pantry_Button);
        freezerButton = findViewById(R.id.freezer_Button);
        fridgeButton = findViewById(R.id.fridge_Button);
        addFoodButton = findViewById(R.id.add_item_button);

        recyclerView = findViewById(R.id.myRecyclerView);
        updateRecycler();

        pantryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        freezerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //set back to all foods list
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
        });

        fridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //set back to all foods list
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
        });

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFoodActivity();
            }
        });
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
}
