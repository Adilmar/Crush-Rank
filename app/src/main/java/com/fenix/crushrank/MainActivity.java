package com.fenix.crushrank;

/**
 * Created by adilmar on 10/02/17.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    //String JsonURL="https://love-calculator.p.mashape.com/getPercentage?fname=John&sname=Alice";
    TextView results;
    String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    String data = "";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        requestQueue = Volley.newRequestQueue(this);

        results = (TextView) findViewById(R.id.jsonData);

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL, null,
                new Response.Listener<JSONObject>(){


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("colorObject");

                            String color = obj.getString("colorName");
                            String desc = obj.getString("description");

                            data += "Color Name: " + color +
                                    "\n" +
                                    "Description : " + desc;

                            results.setText(data);
                        }

                        catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                });

//        {
//
//
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                Map<String, String>  params = new HashMap<String, String>();
////                params.put("X-Mashape-Key", "kpJ1ibkBXPmshdrqB9vN27L2KT7Wp1Mbaa6jsnm6vsjMldszQn");
////                params.put("Accept", "application/json");
////                return params;
////            }
////        };


        requestQueue.add(obreq);
    }
}


