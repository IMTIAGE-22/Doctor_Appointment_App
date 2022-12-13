package com.example.doctorshome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Login_button,SignUp_button;
    private EditText Email,Password;
    private FirebaseAuth loginauth;
    private ProgressBar progressBar2;
    private TextView Forgot_Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Welcome to Doctor's Home");
        Login_button = findViewById(R.id.Sign_IN_Button);
        SignUp_button = findViewById(R.id.Register);
        Email = findViewById(R.id.Login_Email);
        Password = findViewById(R.id.Password);
        progressBar2 = findViewById(R.id.Login_Progressbar);
        Forgot_Password = findViewById(R.id.Forgot_Password);
        loginauth = FirebaseAuth.getInstance();
        Login_button.setOnClickListener(this);
        SignUp_button.setOnClickListener(this);
        Forgot_Password.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Sign_IN_Button:
            {
                userLogin();
                break;
            }
            case R.id.Register:
            {
                Register();
                break;
            }
            case R.id.Forgot_Password:
            {
                forgot_password();
                break;
            }
        }

    }

    private void forgot_password() {
        Intent intent = new Intent(getApplicationContext(),Forgot_Password.class);
        startActivity(intent);

    }

    public void PatientDashboard()
    {
        Intent intent = new Intent(getApplicationContext(),PatientDashboard.class);
        startActivity(intent);
    }
    public void Register()
    {

        Intent intent = new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }
    public void MainActivity()
    {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    private void userLogin() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
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
        if(password.isEmpty())
        {
            Password.setError("Enter a valid password");
            Password.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            Password.setError("Minimum length of a password should be 6");
            Password.requestFocus();
            return;
        }
        progressBar2.setVisibility(View.VISIBLE);
        loginauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar2.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    finish();
                    PatientDashboard();
                }
                else
                {
                    finish();
                    Toast.makeText(getApplicationContext(),"Wrong email or password",Toast.LENGTH_SHORT).show();
                    MainActivity();

                }

            }
        });
    }


}