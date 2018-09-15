package com.example.shaban.caraccessories.model;

public class Accessory {

    String title;

    String description;

    String price = "-1";

    int imgResourceId;

    boolean favorite = false;

    public Accessory(String title, String description, String price, int imgResourceId, boolean favorite) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imgResourceId = imgResourceId;
        this.favorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
