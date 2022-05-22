package com.kevinandrianasolo.m1p9android.services;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.kevinandrianasolo.m1p9android.models.Course;
import com.kevinandrianasolo.m1p9android.models.CourseTheme;
import com.kevinandrianasolo.m1p9android.singleton.ApiSingleton;
import com.kevinandrianasolo.m1p9android.utils.PropertiesUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CourseService {
    public String serverUrl = "http://10.0.2.2:3000";
    private Context context;

    public CourseService(Context context) {

        this.context = context;
        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance(context);
        Properties properties = propertiesUtils.getProperties();
        if(properties!=null) {
            serverUrl = properties.getProperty("server.url");
        }
    }

    public interface allCourseByTheme {
        void onError(String message);
        void onResponse(List<Course> courseList);
    }

    public interface allCourseByTitle {
        void onError(String message);
        void onResponse(List<Course> courseList);
    }


    public void  getAllCourseByTheme(int courseTheme_id,allCourseByTheme allCourseByTheme) {
        String url = serverUrl+"/api/course/theme/"+courseTheme_id;

        List<Course> courseList =  new ArrayList<Course>();
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
                                int courseTheme_id = responseObj.getInt("coursetheme_id");
                                String title= responseObj.getString("title");
                                String description = responseObj.getString("description");
                                String video_path = responseObj.getString("video_path");
                                String thumbnail = responseObj.getString("thumbnail");
                                courseList.add(new Course(id,courseTheme_id,title, description, video_path,thumbnail));
                            }
                          allCourseByTheme.onResponse(courseList);
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred

                    }
                }
        )
        {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            String token = "" ;
            headers.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwidXNlcklkIjoxLCJpYXQiOjE2NTMyNDgzNjYsImV4cCI6MTY1MzI1OTE2Nn0.0ksRQ0ykpR0Qtki5-YWsG12xet82KbCH_9z2kl4RUxA");
            return headers;
        }
        };
        ApiSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
    public void  getAllCourseByTitle(int courseTheme_id,String key,allCourseByTitle allCourseByTitle) {
        String url = serverUrl+"/api/course/theme/"+courseTheme_id+"/search?title="+key;
        List<Course> courseList =  new ArrayList<Course>();
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
                                int courseTheme_id = responseObj.getInt("coursetheme_id");
                                String title= responseObj.getString("title");
                                String description = responseObj.getString("description");
                                String video_path = responseObj.getString("video_path");
                                String thumbnail = responseObj.getString("thumbnail");
                                courseList.add(new Course(id,courseTheme_id,title, description, video_path,thumbnail));
                            }
                            allCourseByTitle.onResponse(courseList);
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred

                    }
                }
        )
        {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                String token = "" ;
                headers.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwidXNlcklkIjoxLCJpYXQiOjE2NTMyNDgzNjYsImV4cCI6MTY1MzI1OTE2Nn0.0ksRQ0ykpR0Qtki5-YWsG12xet82KbCH_9z2kl4RUxA");
                return headers;
            }
        };
        ApiSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }


}
