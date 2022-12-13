package com.example.doctorshome;

import static android.content.ContentValues.TAG;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.BreakIterator;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    private Button button;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private ProgressBar progressBar;
    String userID;

    private EditText Full_Name_text, Email_text, Contact_no_text,pass,confirm_pass,NID_Number;
    String[] genders = {"Male", "Female","Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Register Page");
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.Register_Progressbar);
        radioGroup = findViewById(R.id.GRB);
        button = findViewById(R.id.Register);
        Full_Name_text = findViewById(R.id.FullName);
        Email_text = findViewById(R.id.E_Mail);
        Contact_no_text = findViewById(R.id.ContactNumber2);
        pass = findViewById(R.id.Registor_password);
        confirm_pass = findViewById(R.id.Registor_confirm_password);
        NID_Number =  findViewById(R.id.NID_NO_PICK);

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                String email = Email_text.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String confirm_password = confirm_pass.getText().toString().trim();
                String phoneNumber = Contact_no_text.getText().toString().trim();
                String fullName = Full_Name_text.getText().toString();
                String NID_NO = NID_Number.getText().toString().trim();

                if(email.isEmpty())
                {
                    Email_text.setError("Enter an Email address");
                    Email_text.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Email_text.setError("Enter a valid Email address");
                    Email_text.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    pass.setError("Enter a valid password");
                    pass.requestFocus();
                    return;
                }
                if(password.length()<8)
                {
                    pass.setError("Minimum length of a password should be 8");
                    pass.requestFocus();
                    return;
                }
                if(confirm_password.isEmpty())
                {
                    confirm_pass.setError("Enter the same password");
                    confirm_pass.requestFocus();
                    return;
                }
                if(confirm_password.length()<8 )
                {
                    confirm_pass.setError("Enter the same password");
                    confirm_pass.requestFocus();
                    return;
                }
                if(!confirm_password.equals(password))
                {
                    confirm_pass.setError("Enter the same password");
                    confirm_pass.requestFocus();
                    return;
                }
                int RadioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(RadioID);
                String gender = (String) radioButton.getText();

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this,"Register is successful",Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Full_Name",fullName);
                            user.put("Email",email);
                            user.put("Contact_Number",phoneNumber);
                            user.put("NID_Number",NID_NO);
                            user.put("Password",password);
                            user.put("Gender",gender);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"OnSuccess: User profile is created for"+userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"OnFailure: "+e.toString());
                                }
                            });
                            MainActivity();
                        }
                        else
                        {
                            Toast.makeText(Register.this,"Register is not successful",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


    }

    public void MainActivity()
    {
        Intent intent = new Intent(Register.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}