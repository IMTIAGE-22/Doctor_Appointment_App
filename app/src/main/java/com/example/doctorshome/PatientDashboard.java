package com.example.doctorshome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PatientDashboard extends AppCompatActivity {

   LinearLayout img_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);
        this.setTitle("Patient Dashboard");

        img_button = (LinearLayout)findViewById(R.id.PDLL_4);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about_doctor();
            }
        });

        img_button = (LinearLayout)findViewById(R.id.PDLL_3);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appointment();
            }
        });

        img_button = (LinearLayout)findViewById(R.id.PDLL_6);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Standings();
            }
        });

        img_button = (LinearLayout)findViewById(R.id.PDLL_5);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visiting_hours();
            }
        });

        img_button = (LinearLayout)findViewById(R.id.PDLL_7);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact_us();
            }
        });

        img_button = (LinearLayout)findViewById(R.id.PDLL_8);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_out();
            }
        });
    }

    public void about_doctor()
    {
        Intent intent = new Intent(this,about_doctor.class);
        startActivity(intent);
    }

    public void Appointment()
    {
        Intent intent = new Intent(this,Appointment.class);
        startActivity(intent);
    }

    public void Standings()
    {
        Intent intent = new Intent(this,Standings.class);
        startActivity(intent);
    }

    public void Visiting_hours()
    {
        Intent intent = new Intent(this,Visiting_hours.class);
        startActivity(intent);
    }

    public void contact_us()
    {
        Intent intent = new Intent(this,contact_us.class);
        startActivity(intent);
    }

    public void Sign_out()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}