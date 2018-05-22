package com.example.a16046512.p05problemstatement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ModifySong extends AppCompatActivity {

    private Song song;
    private EditText editTextId,editTextTitle,editTextSinger,editTextYear;
    private RadioGroup rgs;
    private RadioButton rbs1,rbs2,rbs3,rbs4,rbs5;
    private Button btnUpdate,btnDelete,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("pos");

        editTextId = (EditText)findViewById(R.id.editTextId);
        editTextTitle = (EditText)findViewById(R.id.editTextTitle);
        editTextSinger = (EditText)findViewById(R.id.editTextSinger);
        editTextYear = (EditText)findViewById(R.id.editTextYear);

        editTextId.setText(song.get_id()+"");
        editTextId.setEnabled(false);
        editTextTitle.setText(song.getTitle());
        editTextSinger.setText(song.getSingers());
        editTextYear.setText(song.getYear()+"");

        rgs = (RadioGroup)findViewById(R.id.rgs);

        if(song.getStar() == 5){
            rbs5 = (RadioButton)findViewById(R.id.rbs5);
            rbs5.setChecked(true);
        }else if(song.getStar() == 4){
            rbs4 = (RadioButton)findViewById(R.id.rbs4);
            rbs4.setChecked(true);
        }else if(song.getStar() == 3){
            rbs3 = (RadioButton)findViewById(R.id.rbs3);
            rbs3.setChecked(true);
        }else if(song.getStar() == 2){
            rbs2 = (RadioButton)findViewById(R.id.rbs2);
            rbs2.setChecked(true);
        }
        else if(song.getStar() == 1){
            rbs1 = (RadioButton)findViewById(R.id.rbs2);
            rbs1.setChecked(true);
        }


        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifySong.this);
                dbh.deleteSong(song.get_id());
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });


        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifySong.this);
//                public int updateSong(Song data){
                int selectedButtonId = rgs.getCheckedRadioButtonId();
                RadioButton rbs = (RadioButton) findViewById(selectedButtonId);
                song = new Song(Integer.parseInt(editTextId.getText().toString()),Integer.parseInt(editTextYear.getText().toString()),Integer.parseInt(rbs.getText()+""),editTextTitle.getText().toString(),editTextSinger.getText().toString());
                dbh.updateSong(song);
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
