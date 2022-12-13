package com.example.doctorshome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Appointment extends AppCompatActivity {
    EditText date_picker;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        this.setTitle("Doctor Appointment");

        button = (Button)findViewById(R.id.Register2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientDashboard();
            }
        });

        date_picker = findViewById(R.id.Visiting_date_Picker);
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Appointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        date_picker.setText(date);
                    }

                },year,month,dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()+(1000*60*60*24*3));
                datePickerDialog.show();

            }

        });
    }

    public void PatientDashboard()
    {
        Intent intent = new Intent(this,PatientDashboard.class);
        startActivity(intent);
    }
}