package com.kevinandrianasolo.m1p9android.services;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kevinandrianasolo.m1p9android.models.Course;
import com.kevinandrianasolo.m1p9android.models.User;
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

public class UserService {
    public String serverUrl = "http://10.0.2.2:3000";
    private Context context;

    public UserService(Context context) {

        this.context = context;
        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance(context);
        Properties properties = propertiesUtils.getProperties();
        if(properties!=null) {
            serverUrl = properties.getProperty("server.url");
        }
    }

    public interface login {
        void onError(String message);
        void onResponse(String token);
    }

    public interface profile {
        void onError(String message);
        void onResponse(User user);
    }
    public interface user_id {
        void onError(String message);
        void onResponse(int user_id);
    }


    public void  getUser(int id,profile user) {
        String url = serverUrl+"/api/user/"+id;
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = response.getInt("id");
                            String email = response.getString("email");
                            String name= response.getString("name");
                            String firstname = response.getString("firstname");
                            String username = response.getString("username");
                            String birth = response.getString("birth");
                            String gender = response.getString("gender");
                            user.onResponse(new User(id,email,name,firstname,username,birth,gender));
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

            ////put the token here
            String token = "" ;
            headers.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwidXNlcklkIjoxLCJpYXQiOjE2NTMyNDgzNjYsImV4cCI6MTY1MzI1OTE2Nn0.0ksRQ0ykpR0Qtki5-YWsG12xet82KbCH_9z2kl4RUxA");
            return headers;
        }
        };
        ApiSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
    //GET THE TOKEN
    public void  login(login token ,String email,String password) {
        String url = serverUrl+"/api/user/login";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           token.onResponse(response.getString("token"));
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
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String token = "" ;
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        ApiSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
    public void  tokenUser_id(user_id user_id) {
        String url = serverUrl+"/api/user/login";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = response.getInt("id");
                            user_id.onResponse(id);
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
                ////put the token here
                String token = "" ;
                headers.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwidXNlcklkIjoxLCJpYXQiOjE2NTMyNDgzNjYsImV4cCI6MTY1MzI1OTE2Nn0.0ksRQ0ykpR0Qtki5-YWsG12xet82KbCH_9z2kl4RUxA");
                return headers;
            }
        };

        ApiSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }


}
