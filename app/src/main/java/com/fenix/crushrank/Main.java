package com.fenix.crushrank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends AppCompatActivity {

    EditText nome;
    EditText nome2;

    public String usuario;
    public String parceiro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome   = (EditText)findViewById(R.id.nome);
        nome2   = (EditText)findViewById(R.id.crush);



        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                usuario = nome.getText().toString();
                parceiro = nome2.getText().toString();

                Intent it = new Intent(Main.this, MainActivity.class);
                it.putExtra("my_name", usuario);
                it.putExtra("my_name2",parceiro);
                startActivity(it);
            }
        });
    }
}
