package com.fenix.crushrank;

/**
 * Created by adilmar on 10/02/17.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.rating;
import static com.fenix.crushrank.R.id.ratingBar;


public class MainActivity extends AppCompatActivity {

    //String JsonURL="https://love-calculator.p.mashape.com/getPercentage?fname=Adilmar&sname=Adrielly";
    TextView results;
    //String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    String data = "";
    RequestQueue requestQueue;


    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        Intent intent = getIntent();
        String name = intent.getStringExtra("my_name");
        String parceiro=intent.getStringExtra("my_name2");

        String JsonURL="https://love-calculator.p.mashape.com/getPercentage?fname="+name+"&sname="+parceiro;
        Log.d("xxxxxxxxxxxxxxxxxxx",name);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //ratingBar.setRating(3);
        ratingBar.setFocusable(false);

        requestQueue = Volley.newRequestQueue(this);

        results = (TextView) findViewById(R.id.jsonData);

        MyRequest obreq = new MyRequest(Request.Method.GET, JsonURL, null,
                new Response.Listener<JSONObject>(){


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject obj = response.getString();

                            String nome = response.getString("fname");
                            String crush = response.getString("sname");
                            String porcentagem = response.getString("percentage");
                            String resultado = response.getString("result");


                            int por = Integer.parseInt(porcentagem);
                            por = por / 10;

                            ratingBar.setRating(por);

                            if(resultado.equals("Not a good choice.")){

                                resultado = "Não é uma boa escolha";
                            }

                            if(resultado.equals("Can choose someone better.")){

                                resultado = "Você pode escolher alguém melhor";
                            }


                            if(resultado.equals("All the best!")){
                                resultado = "Bem sucedidos!";
                            }

                            if (resultado.equals("Congratulations! Good choice")) {

                                resultado = "Parabéns, excelente escolha";
                            }

                            data += "\nNome: " + nome + "\n" +
                                    "Crush : " + crush+"\n"+
                                    "Porcentagem: "+ porcentagem+"%"+ "\n"+
                                    "Resultado:"+ resultado;

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





        requestQueue.add(obreq);
    }
}


