package com.kevinandrianasolo.m1p9android.services;

import android.content.Context;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CourseThemeService {
    public static final String HTTP_LOCALHOST_3000_API = "http://10.0.2.2:3000/api/";
    private Context context;

    public CourseThemeService(Context context) {
        this.context = context;
    }
    public interface allCourse {
        void onError(String message);
        void onResponse(List<CourseTheme> courseThemeList);
    }

    public void  getAllCourseTheme(int company_id,allCourse allCourse) {
        String url = HTTP_LOCALHOST_3000_API+"courseTheme/"+company_id;

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
