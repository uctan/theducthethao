package com.example.theducthethao.banhang;

public class Dataclass {
    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataImage;
    private boolean isFavorite;

    // Constructors
    public Dataclass() {

    }

    public Dataclass(String dataTitle, String dataDesc, String dataLang, String dataImage, boolean isFavorite) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
        this.isFavorite = isFavorite;
    }

    // Getters and Setters
    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public void setDataLang(String dataLang) {
        this.dataLang = dataLang;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}