package com.example.graduate_corner.users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduate_corner.MainDashboardActivity;
import com.example.graduate_corner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    TextView fnames, txt_mail, txt_cell;
    ImageView email_pic, help_line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        drawerLayout = findViewById(R.id.drawerlayoutprofile);

        email_pic = findViewById(R.id.clickEmail);
        help_line = findViewById(R.id.support_link);

        auth = FirebaseAuth.getInstance();

        fnames = findViewById(R.id.fnames);
        txt_mail = findViewById(R.id.txt_mail);
        txt_cell = findViewById(R.id.txt_cell);

        //Web Page
        help_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.nust.na/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

                try {
                    startActivity(Intent.createChooser(webIntent, "Website Activity"));
                }

                catch (ActivityNotFoundException e){

                    Toast.makeText(ProfileActivity.this, "No Website Opening Applications Found", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //Email
        email_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"support@graduatecorner.biz"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject: Graduate Corner");
                intent.putExtra(Intent.EXTRA_TEXT, "Email body");


                try {
                    startActivity(Intent.createChooser(intent, "Email Graduate Corner"));
                }

                catch (ActivityNotFoundException e){

                    Toast.makeText(ProfileActivity.this, "No Email Opening Application Found", Toast.LENGTH_SHORT).show();

                }
            }
        });

        showAllUserData();

    }

    private void showAllUserData() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String user_id = user.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Software").child("Namibia").child(user_id);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String name = snapshot.child("Full_Name").getValue().toString().toLowerCase();
                    String email = snapshot.child("Email").getValue().toString().toLowerCase();
                    String institution = snapshot.child("Institution").getValue().toString();

                    fnames.setText(name);
                    txt_mail.setText(email);
                    txt_cell.setText(institution);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ProfileActivity.this, "Sorry, Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    //Drawer Code Starts Here
    public void clickMenu(View view){
        //open Drawer
        openDrawer(drawerLayout);
    }

    private static void  openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }//End of Drawer Code

    public void clickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public void clickDashboard(View view){
        //Recreate Activity
        // recreate();
        redirectActivity(this, MainDashboardActivity.class);
    }

    public void clickProfile(View view){
        redirectActivity(this, ProfileActivity.class);
    }

    public void logout(View view){
        //Log the user out of their Account
        //Get Out of the App
        // System.exit(0);
        auth.signOut();
        finish();
    }

    //Taking the User to the Start Activity
    private static void redirectActivity(Activity activity, Class aClass) {

        //initialize intent
        Intent intent = new Intent(activity,aClass);
        //setFlag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start Activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        closeDrawer(drawerLayout);
        super.onPause();
    }



}

