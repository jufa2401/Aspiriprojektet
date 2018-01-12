package com.aspiri.karakterloeft.games;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Justin on 10/01/2018.
 */

public class Flipcard implements Comparable<Flipcard>, Serializable {
    private int id;
    private String category;
    private String front;
    private String back;
    private String photo;


    public Flipcard() {

    }

    public Flipcard(String category, String front, String back, String photo) {
        this.category = category;
        this.front = front;
        this.back = back;
        this.photo = photo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getPhoto(){return photo;}

    public void setPhoto(String photo){ this.photo = photo; }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int compareTo(@NonNull Flipcard flipcard) {
        String s = flipcard.getCategory();
        return s.compareTo(this.getCategory());
    }



}
