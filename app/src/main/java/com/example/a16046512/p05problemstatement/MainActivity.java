package com.example.a16046512.p05problemstatement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etTitle,etSinger,etYear;
    private RadioGroup rg;
    private Button btnInsert,btnShowList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("P05-NDPSongs ~ Insert Song");
        etTitle = (EditText)findViewById(R.id.etTitle);
        etSinger = (EditText)findViewById(R.id.etSinger);
        etYear = (EditText)findViewById(R.id.etYear);



        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnShowList = (Button)findViewById(R.id.btnShowList);

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ShowSong.class);
                startActivity(i);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rg = (RadioGroup)findViewById(R.id.rg);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);
                Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_LONG).show();

                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                String year = etYear.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insertSong(title,singer,Integer.parseInt(year),Integer.parseInt(rb.getText().toString()));
                dbh.close();

                if (row_affected != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}
