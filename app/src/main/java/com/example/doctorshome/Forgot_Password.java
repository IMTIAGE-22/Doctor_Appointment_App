package com.example.doctorshome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    private EditText Email;
    private Button button;
    private ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Email = findViewById(R.id.Forgot_email);
        button = findViewById(R.id.Reset_password_button);
        progressBar = findViewById(R.id.Forgot_password_progessbar);
        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset_password();
            }
        });

    }
    private void reset_password()
    {
        String email = Email.getText().toString().trim();
        if(email.isEmpty())
        {
            Email.setError("Enter an Email address");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Email.setError("Enter a valid Email address");
            Email.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    Toast.makeText(Forgot_Password.this,"Check your email to reset your password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Forgot_Password.this,"Try again please!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}