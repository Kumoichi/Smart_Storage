package com.example.smart_storage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add_food_activity extends AppCompatActivity {

    Button pantryButton2, freezerButton2, fridgeButton2, doneButton;
    EditText itemName;
    TextView date;
    int storageType = 0;
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
        itemName = findViewById(R.id.enter_item);
        date = findViewById(R.id.date);

        pantryButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout2.setBackgroundColor(Color.parseColor("#bf9573"));
                pantryButton2.setTypeface(null, Typeface.BOLD_ITALIC);
                freezerButton2.setTypeface(null, Typeface.NORMAL);
                fridgeButton2.setTypeface(null, Typeface.NORMAL);
                storageType = 1;
            }
        });

        freezerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout2.setBackgroundColor(Color.parseColor("#304bff"));
                freezerButton2.setTypeface(null, Typeface.BOLD_ITALIC);
                pantryButton2.setTypeface(null, Typeface.NORMAL);
                fridgeButton2.setTypeface(null, Typeface.NORMAL);
                storageType = 2;
            }
        });

        fridgeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout2.setBackgroundColor(Color.parseColor("#abb6ff"));
                fridgeButton2.setTypeface(null, Typeface.BOLD_ITALIC);
                pantryButton2.setTypeface(null, Typeface.NORMAL);
                freezerButton2.setTypeface(null, Typeface.NORMAL);
                storageType = 3;
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemText = itemName.getText().toString().trim();
                if (!itemText.isEmpty() && storageType != 0) { // *KENICHI* add this when you finish the calendar input:     && !date.getHint().toString().equals("Date")
                    MainActivity.setItem(itemName.getText());
                    MainActivity.setDate(String.valueOf(date.getText()));
                    MainActivity.setStorageType(String.valueOf(storageType));
                    openMainActivity();
                }
//                else if (itemName.getHint().toString().equals("Enter Item") && !date.getHint().toString().equals("Date") && storageType != 0) {
//                    Toast.makeText(getApplicationContext(), "Please add a name for the item", Toast.LENGTH_LONG).show();
//                } else if (itemName.getHint().toString().equals("Enter Item") && date.getHint().toString().equals("Date") && storageType != 0) {
//                    Toast.makeText(getApplicationContext(), "Please add a name and date for the item", Toast.LENGTH_LONG).show();
//                } else if (itemName.getHint().toString().equals("Enter Item") && date.getHint().toString().equals("Date") && storageType == 0) {
//                    Toast.makeText(getApplicationContext(), "Please add a name, date and storage type for the item", Toast.LENGTH_LONG).show();
//                } else if (!itemName.getHint().toString().equals("Enter Item") && !date.getHint().toString().equals("Date") && storageType == 0) {
//                    Toast.makeText(getApplicationContext(), "Please add a storage type for the item", Toast.LENGTH_LONG).show();
//                } else if (!itemName.getHint().toString().equals("Enter Item") && date.getHint().toString().equals("Date") && storageType == 0) {
//                    Toast.makeText(getApplicationContext(), "Please add a date and storage type for the item", Toast.LENGTH_LONG).show();
//                } else if (!itemName.getHint().toString().equals("Enter Item") && date.getHint().toString().equals("Date") && storageType != 0) {
//                    Toast.makeText(getApplicationContext(), "Please add a date for the item", Toast.LENGTH_LONG).show();
//                } else if (itemName.getHint().toString().equals("Enter Item") && !date.getHint().toString().equals("Date") && storageType == 0) {
//                    Toast.makeText(getApplicationContext(), "Please add a name and storage type for the item", Toast.LENGTH_LONG).show();
//                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter the missing data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
