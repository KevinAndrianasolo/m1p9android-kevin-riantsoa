package com.kevinandrianasolo.m1p9android.models;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private String name;
    private String fistname;
    private String username;
    private String birth;
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(int id, String email, String name, String fistname, String username, String birth, String gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.fistname = fistname;
        this.username = username;
        this.birth = birth;
        this.gender = gender;
    }
}
