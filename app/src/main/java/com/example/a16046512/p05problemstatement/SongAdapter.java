package com.example.a16046512.p05problemstatement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    private ArrayList<Song> song;
    private Context context;
    private TextView rowyear, rowtitle, rowsinger;
    private ImageView one,two,three,four,five;

    public SongAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        song = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    @Override
    public View getView(int position, View converview, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.showsongrow, parent, false);

        rowsinger= (TextView)rowView.findViewById(R.id.rowsinger);
        rowtitle= (TextView)rowView.findViewById(R.id.rowtitle);
        rowyear= (TextView)rowView.findViewById(R.id.rowyear);

        one = (ImageView)rowView.findViewById(R.id.one);
        two = (ImageView)rowView.findViewById(R.id.two);
        three = (ImageView)rowView.findViewById(R.id.three);
        four = (ImageView)rowView.findViewById(R.id.four);
        five = (ImageView)rowView.findViewById(R.id.five);

        Song currentSong = song.get(position);
        rowsinger.setText(currentSong.getSingers());
        rowyear.setText(Integer.toString(currentSong.getYear()));
        rowtitle.setText(currentSong.getTitle());



        if (currentSong.getStar()==1) {
            one.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(currentSong.getStar()==2) {
            one.setImageResource(android.R.drawable.btn_star_big_on);
            two.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(currentSong.getStar()==3) {
            three.setImageResource(android.R.drawable.btn_star_big_on);
            two.setImageResource(android.R.drawable.btn_star_big_on);
            one.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(currentSong.getStar()==4) {
            four.setImageResource(android.R.drawable.btn_star_big_on);
            three.setImageResource(android.R.drawable.btn_star_big_on);
            two.setImageResource(android.R.drawable.btn_star_big_on);
            one.setImageResource(android.R.drawable.btn_star_big_on);
        }else if (currentSong.getStar()==5) {
            five.setImageResource(android.R.drawable.btn_star_big_on);
            four.setImageResource(android.R.drawable.btn_star_big_on);
            three.setImageResource(android.R.drawable.btn_star_big_on);
            two.setImageResource(android.R.drawable.btn_star_big_on);
            one.setImageResource(android.R.drawable.btn_star_big_on);
        }
        return rowView;
    }
}