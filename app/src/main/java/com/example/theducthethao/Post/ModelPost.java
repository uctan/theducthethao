package com.example.theducthethao.Post;

public class ModelPost {
    private String uid;
    private String uName;
    private String pTitle;
    private String pDescr;
    private String pImage;
    private String pTime;

    private String pLikes;

    public ModelPost() {
        // Empty constructor needed for Firestore
    }

    public ModelPost(String uid, String uName, String pTitle, String pDescr, String pImage, String pTime,String pLikes) {
        this.uid = uid;
        this.uName = uName;
        this.pTitle = pTitle;
        this.pDescr = pDescr;
        this.pImage = pImage;
        this.pTime = pTime;
        this.pLikes = pLikes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpDescr() {
        return pDescr;
    }

    public void setpDescr(String pDescr) {
        this.pDescr = pDescr;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }
    public String getpLikes() {
        return pLikes;
    }

    public void setpLikes(String pLikes) {
        this.pLikes = pLikes;
    }
}