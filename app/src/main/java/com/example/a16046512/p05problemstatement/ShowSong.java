package com.example.a16046512.p05problemstatement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowSong extends AppCompatActivity {

    private ListView songlist;
    private Button btnfilter;

    private SongAdapter sa;
    private ArrayList<Song> song;
    private DBHelper dbh = new DBHelper(ShowSong.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);
        songlist = (ListView)findViewById(R.id.songlist);
        btnfilter = (Button)findViewById(R.id.btnfilter);

        song = dbh.getAllSongs();
        sa = new SongAdapter(ShowSong.this,R.layout.showsongrow,song);
        songlist.setAdapter(sa);
        songlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent i = new Intent(ShowSong.this,ModifySong.class);
                Song currentposition = song.get(pos);
                startActivityForResult(i,9);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 9){
            song.clear();
            DBHelper dbh = new DBHelper(this);
            song.addAll(dbh.getAllSongs());
            sa.notifyDataSetChanged();
            Toast.makeText(ShowSong.this, "Update successful",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
