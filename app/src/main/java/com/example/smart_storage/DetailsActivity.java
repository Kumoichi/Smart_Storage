package com.example.smart_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
        if (getIntent().hasExtra("title") && getIntent().hasExtra("expiration") && getIntent().hasExtra("storageType")) {
            str1 =getIntent().getStringExtra("detailTitle");
            str2 = getIntent().getStringExtra("detailExpiration");
            str3 = getIntent().getStringExtra("detailStorage");
        }

        else
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
    }

    private void setData() {
        detailTitle.setText(str1);
        detailExpiry.setText(str2);
        detailStorage.setText(str3);
    }
}
