package com.example.graduate_corner.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduate_corner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ProfileActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    TextView fnames, txt_mail, txt_cell, txt_website;
    ImageView email_pic, whatsapp_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        drawerLayout = findViewById(R.id.drawerlayoutprofile);

        txt_website = findViewById(R.id.support_link);
        email_pic = findViewById(R.id.clickEmail);
        whatsapp_pic = findViewById(R.id.whatsapp_link);

        auth = FirebaseAuth.getInstance();

        fnames = findViewById(R.id.fnames);
        txt_mail = findViewById(R.id.txt_mail);
        txt_cell = findViewById(R.id.txt_cell);



    }
}

