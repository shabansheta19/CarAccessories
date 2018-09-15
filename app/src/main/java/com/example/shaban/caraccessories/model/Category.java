package com.example.shaban.caraccessories.model;

public class Category {

    /* name of the category */
    private String title;

    /* logo image of the category*/
    private int imgResourceId = -1;

    public Category (String title, int imgResourceId) {
        this.title = title;
        this.imgResourceId = imgResourceId;
    }

    /**
     * getter of the title
     * @return the title of the category
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter of logo image
     * @return the logo resource image ID
     */
    public int getImgResourceId() {
        return imgResourceId;
    }

    /**
     * if has logo image return true
     * otherwise returns false
     */
    public boolean hasLogo() {
        if (imgResourceId == -1)
            return false;
        return true;
    }

}
