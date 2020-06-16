package com.example.smart_storage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class add_food_activity extends AppCompatActivity {

    Button pantryButton2, freezerButton2, fridgeButton2, doneButton;
    View constraintLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_activity);
        constraintLayout2 = findViewById(R.id.constraint_Layout2);
        pantryButton2 = findViewById(R.id.pantry_Button2);
        freezerButton2 = findViewById(R.id.freezer_Button2);
        fridgeButton2 = findViewById(R.id.fridge_Button2);
        doneButton = findViewById(R.id.done_button);

        pantryButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout2.setBackgroundColor(Color.parseColor("#bf9573"));
                pantryButton2.setTypeface(null, Typeface.BOLD_ITALIC);
                freezerButton2.setTypeface(null, Typeface.NORMAL);
                fridgeButton2.setTypeface(null, Typeface.NORMAL);
            }
        });

        freezerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout2.setBackgroundColor(Color.parseColor("#304bff"));
                freezerButton2.setTypeface(null, Typeface.BOLD_ITALIC);
                pantryButton2.setTypeface(null, Typeface.NORMAL);
                fridgeButton2.setTypeface(null, Typeface.NORMAL);
            }
        });

        fridgeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout2.setBackgroundColor(Color.parseColor("#abb6ff"));
                fridgeButton2.setTypeface(null, Typeface.BOLD_ITALIC);
                pantryButton2.setTypeface(null, Typeface.NORMAL);
                freezerButton2.setTypeface(null, Typeface.NORMAL);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
