package com.academics.hospitalmanagementsystem;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.HashMap;
import android.os.Bundle;

public class BookAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        EditText editDoctorName = findViewById(R.id.bookAppointmentDoctorName);
        EditText editHospital = findViewById(R.id.bookAppointmentHospital);
        EditText editDate = findViewById(R.id.bookAppointmentDate);
        EditText editTime = findViewById(R.id.bookAppointmentTime);
        EditText editPatientName = findViewById(R.id.bookAppointmentPatientName);
        EditText editPhone = findViewById(R.id.bookAppointmentPhone);
        Button  bookBtn = findViewById(R.id.btnBook);

        String doctorName = getIntent().getStringExtra("doctorName");
        String hospitalName = getIntent().getStringExtra("hospitalName");

        editDoctorName.setText(doctorName);
        editHospital.setText(hospitalName);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookAppointmentActivity.this,
                        (view, year, month, dayOfMonth) -> {
                            String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                            editDate.setText(date);
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(BookAppointmentActivity.this,
                        (view, hourOfDay, minute1) -> {
                            String time = String.format("%02d:%02d", hourOfDay, minute1);
                            editTime.setText(time);
                        },
                        hour,
                        minute,
                        false);
                timePickerDialog.show();
            }
        });
        bookBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String patientName = editPatientName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String date = editDate.getText().toString().trim();
                String time = editTime.getText().toString().trim();

                if (patientName.isEmpty() || phone.isEmpty() || date.isEmpty() || time.isEmpty()) {
                    Toast.makeText(BookAppointmentActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("appointments").push();

                HashMap<String, Object> data = new HashMap<>();
                data.put("doctorName", doctorName);
                data.put("hospitalName", hospitalName);
                data.put("patientName", patientName);
                data.put("phone", phone);
                data.put("date", date);
                data.put("time", time);

                dbRef.setValue(data)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(BookAppointmentActivity.this, "Appointment Booked Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(BookAppointmentActivity.this, "Booking failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}