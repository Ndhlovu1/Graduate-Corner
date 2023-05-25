package com.example.graduate_corner.notes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduate_corner.R;
import com.example.graduate_corner.notes.Models.Notes;
import com.example.graduate_corner.notes.NotesClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }



    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.title_tv.setText(list.get(position).getTitle());
        holder.title_tv.setSelected(true);

        holder.text_view_notes.setText(list.get(position).getNotes());

        holder.text_view_date.setText(list.get(position).getDate());
        holder.text_view_date.setSelected(true);

        if (list.get(position).isPinned()){
            holder.image_view_pin.setImageResource(R.drawable.ic_pin);
        }

        else {
            holder.image_view_pin.setImageResource(0);
        }

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));
        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

       holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container );
               return true;
           }
       });

    }

    private int getRandomColor(){
         List<Integer> colorCode = new ArrayList<>();
         colorCode.add(R.color.color1);
         colorCode.add(R.color.color2);
         colorCode.add(R.color.color3);
         colorCode.add(R.color.color4);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

//Class for the viewholder
class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView title_tv, text_view_notes,text_view_date;
    ImageView image_view_pin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_container = itemView.findViewById(R.id.notes_container);
        title_tv = itemView.findViewById(R.id.title_tv);
        text_view_notes = itemView.findViewById(R.id.text_view_notes);
        text_view_date = itemView.findViewById(R.id.text_view_date);
        image_view_pin = itemView.findViewById(R.id.image_view_pin);



    }



}
