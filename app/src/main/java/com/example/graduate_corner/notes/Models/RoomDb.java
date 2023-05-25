package com.example.graduate_corner.notes.Models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {

    //DB Instance
    private static RoomDb database;
    private static String DATABASE_NAME = "GraduatesCorner";

    //
    public synchronized static RoomDb getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                RoomDb.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;

    }

    //Instance of Dao
    public abstract NotesDAO notesDAO();


}
