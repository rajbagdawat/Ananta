package com.shiva.ananta.models;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class AnantaPostGS {
    String profileimg ,username,topic;
//    int [] images;
  ArrayList<String> arrayList;
  boolean isChecked;

    public AnantaPostGS(String profileimg, String username, String topic,ArrayList<String> arrayList) {
        this.profileimg = profileimg;
        this.username = username;
        this.topic = topic;
        this.arrayList = arrayList;
        this.isChecked = false;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

//    public int[] getImages() {
//        return images;
//    }
//
//    public void setImages(int[] images) {
//        this.images = images;
//    }

}
