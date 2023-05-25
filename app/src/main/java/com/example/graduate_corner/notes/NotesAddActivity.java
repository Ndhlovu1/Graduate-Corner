package com.example.graduate_corner.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.graduate_corner.R;
import com.example.graduate_corner.notes.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class NotesAddActivity extends AppCompatActivity {

    //Create Variables
    EditText editTitle, editNotes;
    ImageView imageSave;
    Notes notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notes_add);

        imageSave = findViewById(R.id.save_image_view);
        editTitle = findViewById(R.id.edit_text_title);
        editNotes = findViewById(R.id.edit_text_notes);

        //
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert to String
                String title = editTitle.getText().toString();
                String description = editNotes.getText().toString();

                if (description.isEmpty()){
                    Toast.makeText(NotesAddActivity.this, "Description can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Get the Date
                SimpleDateFormat formatter = new SimpleDateFormat("EEE,  d MMM yyy HH:mm a");
                Date date = new Date();

                //Initialize Notes then pass it all onto the main activity
                notes = new Notes();

                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));


                Intent intent = new Intent();
                //Ensure the class is serializable
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });

    }
}