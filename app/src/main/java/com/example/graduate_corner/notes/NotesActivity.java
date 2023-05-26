package com.example.graduate_corner.notes;

import static com.example.graduate_corner.R.id.stick;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.graduate_corner.MainDashboardActivity;
import com.example.graduate_corner.R;
import com.example.graduate_corner.notes.Models.Notes;
import com.example.graduate_corner.notes.Models.NotesDAO;
import com.example.graduate_corner.notes.Models.RoomDb;
import com.example.graduate_corner.notes.adapters.NotesListAdapter;
import com.example.graduate_corner.users.LoginActivity;
import com.example.graduate_corner.users.ProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    //Create the specific variables for Firebase
    FirebaseAuth auth;
    DrawerLayout drawerLayout;

    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    List<Notes> notes = new ArrayList<>();
    RoomDb database;
    FloatingActionButton fab_add;

    SearchView searchView_Notes;

    Notes selectedNote;

    MenuItem pin, delete;

    ImageView delete_note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notes);

        drawerLayout = findViewById(R.id.drawerlayoutNotes);
        auth = FirebaseAuth.getInstance();

        searchView_Notes = findViewById(R.id.search_view);

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);

        database = RoomDb.getInstance(this);
        notes = database.notesDAO().getAll();

        pin = findViewById(R.id.stick);
        delete = findViewById(R.id.delete);


        updateRecycler(notes);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NotesActivity.this, NotesAddActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        //For the Search view
        searchView_Notes.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);

                return true;
            }
        });

    }

    private void filter(String newText) {
        List<Notes> filteredList = new ArrayList<>();
        for (Notes singleNote : notes){
            if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase()) || singleNote.getNotes().toLowerCase().contains(newText.toLowerCase()) ){
                filteredList.add(singleNote);
            }
        }

        notesListAdapter.filteredList(filteredList);


    }

    //Accept the Data passed
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            if (resultCode == Activity.RESULT_OK){
                //Save the data to the db
                Notes new_notes = (Notes) data.getSerializableExtra("note");
                //Add it to the db
                database.notesDAO().insert(new_notes);
                //Update arraylist
                notes.clear();
                //Recall all items
                notes.addAll(database.notesDAO().getAll());
                //Update adapter on added Notes
                notesListAdapter.notifyDataSetChanged();

            }
        } else if (requestCode==102) {
            if (resultCode == Activity.RESULT_OK){
                Notes new_notes = (Notes) data.getSerializableExtra("note");
                database.notesDAO().update(new_notes.getID(), new_notes.getTitle(), new_notes.getNotes());

                notes.clear();
                notes.addAll(database.notesDAO().getAll());
                notesListAdapter.notifyDataSetChanged();

            }

        }

    }

    private void updateRecycler(List<Notes> notes) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter=new NotesListAdapter(NotesActivity.this, notes, notesClickListener );
        recyclerView.setAdapter(notesListAdapter);

    }

    private final NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onClick(Notes notes) {
            Intent intent = new Intent(NotesActivity.this, NotesAddActivity.class);
            intent.putExtra("old_note",notes);
            startActivityForResult(intent, 102);
        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {
            selectedNote = new Notes();
            selectedNote = notes;
            showPopUp(cardView);


        }
    };

    public void showPopUp(CardView cardView) {
        //Create the Delete and the Pin Menu Items
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();

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
    public boolean onMenuItemClick(MenuItem item) {



        return false;
    }
}