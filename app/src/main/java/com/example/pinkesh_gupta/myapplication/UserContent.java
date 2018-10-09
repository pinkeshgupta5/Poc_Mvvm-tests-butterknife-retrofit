package com.example.pinkesh_gupta.myapplication;

public class UserContent {
    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }

    private String description;
    private String imageHref;
    private String title;

    public UserContent(String title,String description,String imageHref)
    {
        this.title=title;
        this.description=description;
        this.imageHref=imageHref;

    }
}
