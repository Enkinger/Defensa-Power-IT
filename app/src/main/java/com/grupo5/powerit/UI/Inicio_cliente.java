package com.grupo5.powerit.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo5.powerit.R;

public class Inicio_cliente extends AppCompatActivity {

    private Button btncrearticket;
    private String valor;
    private Button btnTicketsActivos,btnHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_cliente);
        btncrearticket = findViewById( R.id.btn_crear_ticket );
        valor = getIntent().getStringExtra("nombre_usuario");
        btnTicketsActivos = findViewById(R.id.btnTicketsActivos);
        btnHistorial = findViewById(R.id.btn_historialS);

        btncrearticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearTicket();
            }

            private void CrearTicket() {
                Intent intent = new Intent (Inicio_cliente.this, LevantarTicket.class);
                intent.putExtra("nombre_usuario", valor);
                startActivity(intent);
            }
        });

        btnTicketsActivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inicio_cliente.this, TicketsActivos.class).putExtra("nombre_usuario", valor));
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inicio_cliente.this, HistroialTicketCliente.class).putExtra("nombre_usuario", valor));
            }
        });

    }
}


