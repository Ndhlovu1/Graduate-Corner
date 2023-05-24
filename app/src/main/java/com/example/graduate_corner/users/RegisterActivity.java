package com.example.graduate_corner.users;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.graduate_corner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {

    //Variable Declarations
    EditText full_name, email, institution, cellphone,password;
    Button registerbtn;
    TextView login;

    FirebaseAuth auth;
    DatabaseReference ref;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        // Bind views to Variables

        full_name = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        institution = findViewById(R.id.institution);
        cellphone = findViewById(R.id.cellphone);
        password = findViewById(R.id.password);

        login = findViewById(R.id.txt_login);
        registerbtn = findViewById(R.id.register_btn);

        //Create a Firebase Instance

        auth = FirebaseAuth.getInstance();

        //Redirect to the Login Page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

        //Pass the Data into a separate Registration Method









    }
}