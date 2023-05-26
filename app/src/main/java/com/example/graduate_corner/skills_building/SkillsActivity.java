package com.example.graduate_corner.skills_building;

import static com.example.graduate_corner.R.id.skillsDrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.graduate_corner.MainDashboardActivity;
import com.example.graduate_corner.R;
import com.example.graduate_corner.WebView1Activity;
import com.example.graduate_corner.users.LoginActivity;
import com.example.graduate_corner.users.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SkillsActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DrawerLayout drawerLayout;

    Button coding1,coding2,coding3, finance1,finance2, finance3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_skills);

        drawerLayout = findViewById(R.id.skillsDrawer);
        auth = FirebaseAuth.getInstance();

        Button coding1 = findViewById(R.id.programming_book);
        Button coding2 = findViewById(R.id.programming_book2);
        Button coding3 = findViewById(R.id.programming_book3);


        Button finance1 = findViewById(R.id.finance_book);
        Button finance2 = findViewById(R.id.finance_book2);
        Button finance3 = findViewById(R.id.finance_book3);
        coding1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView1Activity.class);
                intent.putExtra("pdf_url", "https://www.convo-health.com/wp-content/uploads/2023/05/Chamillard-C-Unity-Book.pdf");
                startActivity(intent);
            }
        });

        coding2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView1Activity.class);
                intent.putExtra("pdf_url", "https://www.convo-health.com/wp-content/uploads/2023/05/apress-learn-java-for-web-development-2014.pdf");
                startActivity(intent);
            }
        });

        coding3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView1Activity.class);
                intent.putExtra("pdf_url", "https://www.convo-health.com/wp-content/uploads/2023/05/Full-Stack-AngularJS-for-Java-Developers_-Build-a-Full-Featured-Web-Application-from-Scratch-Using-AngularJS-with-Spring-RESTful-PDFDrive.com-.pdf");
                startActivity(intent);
            }
        });



        finance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView1Activity.class);
                intent.putExtra("pdf_url","https://www.convo-health.com/wp-content/uploads/2023/05/The-Seven-Habits-of-Highly-Effective-People-by-Steven-Covey.pdf");
                startActivity(intent);
            }
        });

        finance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView1Activity.class);
                intent.putExtra("pdf_url","https://www.convo-health.com/wp-content/uploads/2023/05/little-black-Billionaires-Secrets.pdf");
                startActivity(intent);
            }
        });

        finance3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView1Activity.class);
                intent.putExtra("pdf_url","https://www.convo-health.com/wp-content/uploads/2023/05/Emotional_Intelligence_Daniel_Goleman.pdf");
                startActivity(intent);
            }
        });




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


}