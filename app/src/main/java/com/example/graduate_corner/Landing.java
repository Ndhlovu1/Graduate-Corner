package com.example.graduate_corner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.graduate_corner.users.LoginActivity;
import com.example.graduate_corner.users.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Landing extends AppCompatActivity {

    //Declare the buttons in the xml file
    Button lgn_btn, register;
    //Create a firebase user instance
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //Should the user be logged in already, then simply forward to logged in screen
        if (firebaseUser != null){
            startActivity(new Intent(Landing.this, MainDashboardActivity.class));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);

        //Assign the views to their respective variable names
        lgn_btn = findViewById(R.id.login_btn);
        register = findViewById(R.id.register_btn);

        //Launch an explicit event into the Login Activity
        lgn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing.this, LoginActivity.class));
                finish();

            }
        });

        //Launch an explicit event into the Register Activity
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing.this, RegisterActivity.class));
                finish();
            }
        });



    }




}