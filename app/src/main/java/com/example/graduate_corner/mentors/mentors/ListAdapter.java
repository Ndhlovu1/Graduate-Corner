package com.example.graduate_corner.mentors.mentors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.graduate_corner.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<User> {

    public ListAdapter(Context context, ArrayList<User> userArrayList){
        super(context, R.layout.list_item, userArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView userName = convertView.findViewById(R.id.personTitle);
        TextView coach = convertView.findViewById(R.id.coach_type);
        TextView city = convertView.findViewById(R.id.city);


        imageView.setImageResource(Integer.parseInt(String.valueOf(user.imageId)));
        userName.setText(user.name);
        city.setText(user.city);
        coach.setText(user.coach);





        return  convertView;
    }
}
