package com.fenix.crushrank;

/**
 * Created by adilmar on 10/02/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    //String JsonURL="https://love-calculator.p.mashape.com/getPercentage?fname=Adilmar&sname=Adrielly";
    TextView results;
    //String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    String data = "";
    RequestQueue requestQueue;

    private RatingBar ratingBar;
    public String name;
    public String parceiro;
    public String pfinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        Intent intent = getIntent();
        name = intent.getStringExtra("my_name");
        parceiro=intent.getStringExtra("my_name2");

        Button btShareText = ( Button ) findViewById( R.id.share );


        String JsonURL="https://love-calculator.p.mashape.com/getPercentage?fname="+name+"&sname="+parceiro;
        Log.d("Nome",name);

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

                            pfinal = response.getString("percentage");
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
                            Toast.makeText(getApplicationContext(),
                                    "Sem Conexão, tente novamente!", Toast.LENGTH_SHORT).show();
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

        btShareText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                shareText();
            }
        } );
    }

    private void shareText() {
        // cria a intent e define a ação
        Intent intent = new Intent( Intent.ACTION_SEND );
        // tipo de conteúdo da intent
        intent.setType( "text/plain" );
        // string a ser enviada para outra intent
        intent.putExtra( Intent.EXTRA_TEXT, "Crush Rank: Você sabia que:"+name+"&"+parceiro+" tem "+pfinal+"%"+" chaces de darem certo <3"
                +"\n"+"https://play.google.com/store/apps/details?id=com.fenix.chrushrank" );
        // inicia a intent
        startActivity( intent );
    }
}


