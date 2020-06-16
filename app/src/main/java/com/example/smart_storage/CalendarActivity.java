package com.example.smart_storage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    CalendarView mCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dates = year + "/" + month + "/" + dayOfMonth;
                Log.d(TAG, "onSelectedDayChanges: date " + dates);

                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                intent.putExtra("date", dates);
                startActivity(intent);
            }
        });
    }
}
