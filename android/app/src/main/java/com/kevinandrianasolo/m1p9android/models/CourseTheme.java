package com.kevinandrianasolo.m1p9android.models;

import java.io.Serializable;

public class CourseTheme implements Serializable {
    private int id;
    private String name;
    private  int company_id;
    private String description;
    private String image_path;

    public CourseTheme(int id, String name, int company_id, String description, String image_path) {
        this.id = id;
        this.name = name;
        this.company_id = company_id;
        this.description = description;
        this.image_path = image_path;
    }

    public CourseTheme(String title, String description, String img) {
        this.name = title;
        this.description = description;
        this.image_path = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String img) {
        this.image_path = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
