package com.fenix.crushrank;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adilma on 20/02/17.
 */

public class MyRequest extends JsonObjectRequest {
    public MyRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }


    @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Mashape-Key", "kpJ1ibkBXPmshdrqB9vN27L2KT7Wp1Mbaa6jsnm6vsjMldszQn");
                params.put("Accept", "application/json");
                return params;
            }
};


