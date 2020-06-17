package com.example.smart_storage;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView detailTitle, detailExpiry, detailStorage;
    String str1, str2, str3; //temp strings to transfer to textview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailTitle = findViewById(R.id.detailTitle);
        detailExpiry = findViewById(R.id.detailExpiry);
        detailStorage = findViewById(R.id.detailStorage);

        getData();
        setData();
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
