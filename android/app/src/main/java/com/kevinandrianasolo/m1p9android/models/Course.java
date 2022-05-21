package com.kevinandrianasolo.m1p9android.models;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private int courseTheme_id;
    private String title;
    private String description;
    private String video_path;
    private String thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseTheme_id() {
        return courseTheme_id;
    }

    public void setCourseTheme_id(int courseTheme_id) {
        this.courseTheme_id = courseTheme_id;
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

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Course(int id, int courseTheme_id, String title, String description, String video_path, String thumbnail) {
        this.id = id;
        this.courseTheme_id = courseTheme_id;
        this.title = title;
        this.description = description;
        this.video_path = video_path;
        this.thumbnail = thumbnail;
    }
}
