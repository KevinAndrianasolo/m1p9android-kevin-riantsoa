package com.kevinandrianasolo.m1p9android.models;

import java.io.Serializable;

public class Course implements Serializable {
    private String title;
    private String description;
    private String src;

    public Course(String title, String description, String src) {
        this.title = title;
        this.description = description;
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
