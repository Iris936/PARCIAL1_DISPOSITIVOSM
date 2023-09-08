package com.iris.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView TxtUsuario;
    Button BtnAplicaciones,BtnAcercaDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TxtUsuario = findViewById(R.id.TxtUsuario);
        BtnAplicaciones = findViewById(R.id.BtnAplicaciones);
        BtnAcercaDe = findViewById(R.id.BtnAcercaDe);
        String user =getIntent().getStringExtra("usuario");

        TxtUsuario.setText("BIENVENIDO  \n");

        BtnAplicaciones.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        BtnAcercaDe.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, AcercaDe.class);
                startActivity(intent);
            }
        });
    }
}