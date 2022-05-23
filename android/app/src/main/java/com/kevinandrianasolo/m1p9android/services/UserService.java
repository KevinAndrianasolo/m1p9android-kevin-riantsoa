package com.kevinandrianasolo.m1p9android.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.models.Course;
import com.kevinandrianasolo.m1p9android.models.User;
import com.kevinandrianasolo.m1p9android.singleton.ApiSingleton;
import com.kevinandrianasolo.m1p9android.utils.PropertiesUtils;
import com.kevinandrianasolo.m1p9android.utils.SharedPreferencesUtils;

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

    //GET THE TOKEN
    public void  login(String email,String password, login token ) {
        String url = serverUrl+"/api/user/login";
        Map<String, String> params = new HashMap();
        params.put("email", email);
        params.put("password", password);

        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           token.onResponse(response.getString("token"));
                        } catch (JSONException e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Toast.makeText(context, "Compte inexistant", Toast.LENGTH_SHORT).show();
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

    public void  getUserDetails(String tokenUser, profile user) {

        String url = serverUrl+"/api/user/details";
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
                        Toast.makeText(context, "Token invalide", Toast.LENGTH_SHORT).show();
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
                headers.put("Authorization", "Bearer " + tokenUser);
                return headers;
            }
        };

        ApiSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }


}
