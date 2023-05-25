package com.example.graduate_corner.notes;

import androidx.cardview.widget.CardView;

import com.example.graduate_corner.notes.Models.Notes;

public interface NotesClickListener {

    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);

}
