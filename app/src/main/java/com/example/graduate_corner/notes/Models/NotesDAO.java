package com.example.graduate_corner.notes.Models;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Used to perfom the CRUD Declarations with Sql statements
@Dao
public interface NotesDAO {

    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    //Fetch from the DB
    @Query("SELECT * FROM notes ORDER BY id Desc")
    List<Notes> getAll();

    //Update Methods into the database
    @Query("UPDATE notes SET title = :title, notes= :notes WHERE ID= :id")
    void update(int id, String title, String notes);

    //Delete from the DB
    @Delete
    void delete(Notes notes);


}
