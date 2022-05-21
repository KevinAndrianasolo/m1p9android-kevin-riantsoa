package com.kevinandrianasolo.m1p9android.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kevinandrianasolo.m1p9android.models.CourseTheme;
import com.kevinandrianasolo.m1p9android.singleton.ApiSingleton;
import com.kevinandrianasolo.m1p9android.utils.PropertiesUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CourseThemeService {
    public String serverUrl = "http://10.0.2.2:3000";
    private Context context;

    public CourseThemeService(Context context) {
        this.context = context;
        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance(context);
        Properties properties = propertiesUtils.getProperties();
        if(properties!=null) {
            serverUrl = properties.getProperty("server.url");

        }
    }
    public interface allCourse {
        void onError(String message);
        void onResponse(List<CourseTheme> courseThemeList);
    }

    public void  getAllCourseTheme(int company_id,allCourse allCourse) {
        String url =  serverUrl+"/api/courseTheme/"+company_id;
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
        List<CourseTheme> courseThemeList =  new ArrayList<CourseTheme>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0;i<response.length();i++) {
                                JSONObject responseObj = response.getJSONObject(i);
                                int id = responseObj.getInt("id");
                                String coursethemeName = responseObj.getString("name");
                                int company_id = responseObj.getInt("company_id");
                                String description = responseObj.getString("description");
                                String image_path = responseObj.getString("image_path");
                                System.out.println(courseThemeList);
                                courseThemeList.add(new CourseTheme(id,coursethemeName,company_id, description, image_path));
                            }
                            allCourse.onResponse(courseThemeList);
                        } catch (JSONException e) {
                            System.out.println("dsdsdssss"+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        System.out.println("dsdsdsssaaaaaaaaaaaaaaaaaaas"+error.getMessage());

                    }
                }
        );
        ApiSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }


}
