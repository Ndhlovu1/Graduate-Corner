package com.example.graduate_corner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.GridLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainDashboardActivity extends AppCompatActivity {

    //Create the specific variables for Firebase
    FirebaseAuth auth;
    DrawerLayout drawerLayout;
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_dashboard);
    }
}