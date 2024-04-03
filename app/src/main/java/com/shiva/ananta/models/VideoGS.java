package com.shiva.ananta.models;

public class VideoGS {
    String videoUrl;
    String videoimage;
//    Uri videoUrl;
    String videoTitle;
    String videoLike;
    String videoComment;
    String videoShare;
    String videoDesc;
    String uploaderprofile;

    public String getVideoLike() {
        return videoLike;
    }

    public void setVideoLike(String videoLike) {
        this.videoLike = videoLike;
    }

    public String getVideoComment() {
        return videoComment;
    }

    public void setVideoComment(String videoComment) {
        this.videoComment = videoComment;
    }

    public String getVideoShare() {
        return videoShare;
    }

    public void setVideoShare(String videoShare) {
        this.videoShare = videoShare;
    }

    public String getUploaderprofile() {
        return uploaderprofile;
    }

    public void setUploaderprofile(String uploaderprofile) {
        this.uploaderprofile = uploaderprofile;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked;

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public VideoGS(String videoUrl, String videoTitle, String videoDesc,String uploaderprofile,String videoLike
    ,String videoComment,String videoShare,String videoimage ) {
        this.videoUrl = videoUrl;
        this.videoTitle = videoTitle;
        this.videoDesc = videoDesc;
        this.uploaderprofile = uploaderprofile;
        this.videoLike = videoLike;
        this.videoComment = videoComment;
        this.videoShare = videoShare;
        this.videoimage = videoimage;
        this.isChecked = false;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoimage() {
        return videoimage;
    }

    public void setVideoimage(String videoimage) {
        this.videoimage = videoimage;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }
}
