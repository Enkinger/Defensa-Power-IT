package com.grupo5.powerit.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo5.powerit.R;

public class Inicio_Socio extends AppCompatActivity {


    Button btnVerTickets, btnTicketsActivos,btnHistS;
    String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio__socio);

        valor = getIntent().getStringExtra("nombre_socio");

        btnVerTickets = findViewById( R.id.btn_ver_ticket );
        btnTicketsActivos = findViewById(R.id.btnTicketsActivos);
        btnHistS = findViewById(R.id.btn_historialS);


        btnVerTickets.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inicio_Socio.this, TicketsDisponibles.class).putExtra("nombre_socio", valor));
            }
        } );
        btnTicketsActivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inicio_Socio.this, TicketsActivosSocio.class).putExtra("nombre_socio", valor));
            }
        });

        btnHistS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Inicio_Socio.this, HistorilaTicketSocio.class).putExtra("nombre_socio", valor));

            }
        });

    }
}