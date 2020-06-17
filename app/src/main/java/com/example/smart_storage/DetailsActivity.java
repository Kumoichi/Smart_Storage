package com.example.smart_storage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    Button returnButton, deleteButton;
    TextView detailTitle, detailExpiry, detailStorage;
    String str1, str2, str3; //temp strings to transfer to textview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailTitle = findViewById(R.id.detailTitle);
        detailExpiry = findViewById(R.id.detailExpiry);
        detailStorage = findViewById(R.id.detailStorage);
        returnButton = findViewById(R.id.detailReturn);
        deleteButton = findViewById(R.id.detailDelete);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = findIndex(str1);
                deleteItem(index);
            }
        });
        getData();
        setData();
    }

    private int findIndex(String itemName) {
        int index = -1;
        for (int i = 0; i < MainActivity.item.length; i++) {
            if (MainActivity.item[i].equals(itemName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void deleteItem(int index) {

        if (index >= 0) {
            String[] anotherArray = new String[MainActivity.item.length];
            for (int i = 0, k = 0; i < MainActivity.item.length; i++) {
                if (i == index) continue;

                anotherArray[k++] = MainActivity.item[i];
            }
            MainActivity.item = anotherArray;

            String[] anotherArray2 = new String[MainActivity.date.length];
            for (int i = 0, k = 0; i < MainActivity.item.length; i++) {
                if (i == index) continue;

                anotherArray2[k++] = MainActivity.date[i];
            }
            MainActivity.date = anotherArray2;

            String[] anotherArray3 = new String[MainActivity.storageType.length];
            for (int i = 0, k = 0; i < MainActivity.storageType.length; i++) {
                if (i == index) continue;

                anotherArray3[k++] = MainActivity.storageType[i];
            }
            MainActivity.storageType = anotherArray3;
            MainActivity.itemAmount--;
        }
    }

    private void returnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void getData() {
        if (getIntent().hasExtra("detailTitle") && getIntent().hasExtra("detailExpiry") && getIntent().hasExtra("detailStorage")) {
            str1 = getIntent().getStringExtra("detailTitle");
            str2 = getIntent().getStringExtra("detailStorage");
            str3 = getIntent().getStringExtra("detailExpiry");
        } else
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
    }

    private void setData() {
        detailTitle.setText(str1);
        if (str2.equals("1")) detailStorage.setText("Pantry Item");
        if (str2.equals("2")) detailStorage.setText("Freezer Item");
        if (str2.equals("3")) detailStorage.setText("Fridge Item");

        detailExpiry.setText(String.format("Expires: %s", str3));
    }
}
