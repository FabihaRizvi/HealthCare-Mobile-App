package com.academics.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

public class LabTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        CardView LTBlood = findViewById(R.id.LTBlood);
        CardView LTXRay = findViewById(R.id.LTXRay);
        CardView LTDiabetes = findViewById(R.id.LTDiabetes);
        CardView LTCBC = findViewById(R.id.LTCBC);
        CardView LTUrineRoutineTest = findViewById(R.id.LTUrineRoutineTest);
        CardView LTUltrasound = findViewById(R.id.LTUltrasound);
        TextView backOptionLab = findViewById(R.id.backOptionLab);
        backOptionLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });
        setupCardClick(LTBlood, "Blood Test");
        setupCardClick(LTXRay, "X-Ray");
        setupCardClick(LTDiabetes, "Diabetes Test");
        setupCardClick(LTCBC, "CBC");
        setupCardClick(LTUrineRoutineTest, "Urine Routine Test");
        setupCardClick(LTUltrasound, "Ultrasound");
    }

    private void setupCardClick(CardView cardView, String testName){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestActivity.this, LabTestDetailActivity.class);
                intent.putExtra("test_name", testName);
                startActivity(intent);
            }
        });
    }
}