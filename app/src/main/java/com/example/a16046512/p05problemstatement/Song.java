package com.example.a16046512.p05problemstatement;

import java.io.Serializable;

public class Song implements Serializable {
    private int _id,year,star;
    private String title,singers;

    public Song(int id, int year, int star, String title, String singers) {
        this._id = id;
        this.year = year;
        this.star = star;
        this.title = title;
        this.singers = singers;
    }

    public int get_id() {
        return _id;
    }

    public int getYear() {
        return year;
    }

    public int getStar() {
        return star;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }
}
