package com.aspiri.karakterloeft.games;

/**
 * Created by Justin on 10/01/2018.
 */

public class Flipcard {

    private String front;
    private String back;
    private String photo;

    public Flipcard() {

    }

    public Flipcard(String front, String back, String photo) {
        this.front = front;
        this.back = back;
        this.photo = photo;
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

}
