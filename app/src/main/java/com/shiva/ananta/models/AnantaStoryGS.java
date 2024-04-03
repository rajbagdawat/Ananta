package com.shiva.ananta.models;

public class AnantaStoryGS {
    String ProfileImg,Profilename;

    public AnantaStoryGS(String profileImg, String profilename) {
        ProfileImg = profileImg;
        Profilename = profilename;
    }

    public String getProfileImg() {
        return ProfileImg;
    }

    public void setProfileImg(String profileImg) {
        ProfileImg = profileImg;
    }

    public String getProfilename() {
        return Profilename;
    }

    public void setProfilename(String profilename) {
        Profilename = profilename;
    }
}
