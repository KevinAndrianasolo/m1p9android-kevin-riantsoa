package com.kevinandrianasolo.m1p9android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.Toast;

import com.kevinandrianasolo.m1p9android.MainActivity;

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
