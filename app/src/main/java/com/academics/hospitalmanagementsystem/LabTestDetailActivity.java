package com.academics.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LabTestDetailActivity extends AppCompatActivity {
    TextView titleTextView;
    ListView listView;
    ArrayList<String> testDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        titleTextView = findViewById(R.id.DDtitle);
        listView = findViewById(R.id.DDlist);

        String testName = getIntent().getStringExtra("test_name");
        titleTextView.setText(testName);


    }
}