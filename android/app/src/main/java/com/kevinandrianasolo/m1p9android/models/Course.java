package com.kevinandrianasolo.m1p9android.models;

import java.io.Serializable;

public class Course implements Serializable {
    private String title;
    private String description;
    private String src;
    private String thumbnail;

    public Course(String title, String description, String src, String thumbnail) {
        this.title = title;
        this.description = description;
        this.src = src;
        this.thumbnail = thumbnail;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
