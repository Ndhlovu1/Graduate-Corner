package com.example.graduate_corner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.GridLayout;

import com.example.graduate_corner.mentors.MentorsActivity;
import com.example.graduate_corner.notes.NotesActivity;
import com.example.graduate_corner.skills_building.SkillsActivity;
import com.example.graduate_corner.videos.VideosActivity;
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




}