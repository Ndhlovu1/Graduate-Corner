package com.example.graduate_corner.mentors.mentors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.graduate_corner.R;
import com.example.graduate_corner.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){
            String name = intent.getStringExtra("name");
            String title = intent.getStringExtra("title");
            String city = intent.getStringExtra("city");
            int imageid = intent.getIntExtra("imageid", R.drawable.ic_person);

            binding.profileImage.setImageResource(imageid);
            binding.nameProfile.setText(name);
            binding.coachTitle.setText(title);
            binding.cityProfile.setText(city);
        }




    }
}