package com.example.a16046512.p05problemstatement;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "song.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG_TITLE= "title";
    private static final String COLUMN_SONG_SINGER = "singer";
    private static final String COLUMN_SONG_YEAR = "year";
    private static final String COLUMN_SONG_STAR = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Create
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SONG_TITLE + " TEXT,"
                + COLUMN_SONG_SINGER +" TEXT,"
                + COLUMN_SONG_YEAR+" TEXT,"
                + COLUMN_SONG_STAR+" TEXT) ";
        db.execSQL(createNoteTableSql);

        //Dummy records, to be inserted when the database is created
            ContentValues values = new ContentValues();
            values.put(COLUMN_SONG_TITLE,  "Home");
            values.put(COLUMN_SONG_SINGER,   "Kit Chen");
            values.put(COLUMN_SONG_YEAR,   1998);
            values.put(COLUMN_SONG_STAR,  5);
            db.insert(TABLE_SONG, null, values);
            values.put(COLUMN_SONG_TITLE,   "Our Singapore");
            values.put(COLUMN_SONG_SINGER,   "JJ Lin/Dick Lee");
            values.put(COLUMN_SONG_YEAR,   2015);
            values.put(COLUMN_SONG_STAR,   5);
            db.insert(TABLE_SONG, null, values);
            values.put(COLUMN_SONG_TITLE,   "Future in My Dreams");
            values.put(COLUMN_SONG_SINGER,   "SAF Music and Drama Company");
            values.put(COLUMN_SONG_YEAR,   1997);
            values.put(COLUMN_SONG_STAR,   4);
            db.insert(TABLE_SONG, null, values);
        Log.i("tttinfo", "dummy records inserted");

    }

    //Delete
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    //Create table
    public long insertSong(String title,String singer,int year,int star) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, title);
        values.put(COLUMN_SONG_SINGER, singer);
        values.put(COLUMN_SONG_YEAR, year);
        values.put(COLUMN_SONG_STAR, star);
        long result = db.insert(TABLE_SONG, null, values);
        if (result == -1){
            Log.d("tttDBHelper", "Insert failed”");
        }
        Log.d("tttSQL Insert ",""+ result); //id returned, shouldn’t be -1


        db.close();
        return result;
    }

    //read
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        // "SELECT id, note_content, stars FROM note"
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_SONG_TITLE+","
                + COLUMN_SONG_SINGER+","
                + COLUMN_SONG_YEAR+","
                + COLUMN_SONG_STAR
                + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int years = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(id, years, stars,title,singers);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return songs;
    }


    public ArrayList<Song> getSong(int year) {
        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_SONG_TITLE,COLUMN_SONG_SINGER,COLUMN_SONG_YEAR,COLUMN_SONG_STAR};
        String condition = COLUMN_SONG_YEAR + " =?";
        String[] args = {String.valueOf(year)};
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int years = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song song = new Song(id,years,star,title,singer);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getAllStar() {
        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_SONG_TITLE,COLUMN_SONG_SINGER,COLUMN_SONG_YEAR,COLUMN_SONG_STAR};
        String condition = COLUMN_SONG_STAR + " = 5  ";
//        String[] args = { " 5 "};
        Cursor cursor = db.query(TABLE_SONG, columns, condition, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int years = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song song = new Song(id,years,star,title,singer);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return songs;
    }







    //update
    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, data.getTitle());
        values.put(COLUMN_SONG_SINGER, data.getSingers());
        values.put(COLUMN_SONG_YEAR, data.getYear());
        values.put(COLUMN_SONG_STAR, data.getYear());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    //delete
    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }


}
