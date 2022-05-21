package com.kevinandrianasolo.m1p9android.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private static PropertiesUtils instance;
    private Properties properties;
    private PropertiesUtils(Context context){
        try{
            properties = new Properties();
            InputStream is = context.getAssets().open("application.properties");
            properties.load(is);
        }
        catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public static PropertiesUtils getInstance(Context context){
        if(instance==null){
            instance = new PropertiesUtils(context);
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
