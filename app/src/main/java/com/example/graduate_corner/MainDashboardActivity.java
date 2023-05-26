package com.example.graduate_corner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.graduate_corner.mentors.MentorsActivity;
import com.example.graduate_corner.notes.NotesActivity;
import com.example.graduate_corner.skills_building.SkillsActivity;
import com.example.graduate_corner.users.LoginActivity;
import com.example.graduate_corner.users.ProfileActivity;
import com.example.graduate_corner.videos.VideosActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainDashboardActivity extends AppCompatActivity {

    //Create the specific variables for Firebase
    FirebaseAuth auth;
    DrawerLayout drawerLayout;
    GridLayout mainGrid;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        mainGrid = (GridLayout)findViewById(R.id.main_grid_Dash);
        setSingleEvent(mainGrid);

        drawerLayout = findViewById(R.id.drawerlayout);
        auth = FirebaseAuth.getInstance();

    }

    //Set the Direction for the MainGrid Cards being Viewed
    private void setSingleEvent(GridLayout mainGrid) {

        for (int i = 0; i<mainGrid.getChildCount(); i++){

            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(v -> {
                if (finalI == 0){
                    startActivity(new Intent(MainDashboardActivity.this, NotesActivity.class));
                }

                else if (finalI == 1){
                    startActivity(new Intent(MainDashboardActivity.this, SkillsActivity.class));
                }
                else if (finalI == 2){
                    startActivity(new Intent(MainDashboardActivity.this, VideosActivity.class));
                }
                else if (finalI == 3){
                    startActivity(new Intent(MainDashboardActivity.this, MentorsActivity.class));
                }

            });

        }

    }

    //Verify if the user hasnt clicked logout to kill this activity
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null){
            //String userName = firebaseUser.getEmail();
            //Toast.makeText(this, "Welcome Back!", Toast.LENGTH_SHORT).show();
        }else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
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

    @Override
    public void onBackPressed() {
        counter++;
        if (counter == 2){
            super.onBackPressed();
        }

    }


}