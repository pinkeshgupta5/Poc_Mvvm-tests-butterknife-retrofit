package com.example.pinkesh_gupta.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponses {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("title")
    private String title;

    public ArrayList<UserContent> getRows() {
        return rows;
    }

    public void setRows(ArrayList<UserContent> rows) {
        this.rows = rows;
    }

    @SerializedName("rows")
    private ArrayList<UserContent> rows;


}
