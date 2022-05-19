package com.kevinandrianasolo.m1p9android.utils;

import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static SharedPreferencesUtils instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferencesUtils(){

    }
    public static SharedPreferencesUtils getInstance(){
        if(instance==null){
            instance = new SharedPreferencesUtils();
        }
        return instance;
    }

    public SharedPreferences getSharedPreferences(){
        return this.sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }
}
