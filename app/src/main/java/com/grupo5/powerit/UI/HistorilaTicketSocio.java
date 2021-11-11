package com.grupo5.powerit.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.grupo5.powerit.R;
import com.grupo5.powerit.database.AppDataBase;
import com.grupo5.powerit.entities.Ticket;

import java.util.List;

public class HistorilaTicketSocio extends AppCompatActivity {

    private RecyclerView rvTicketsH;

    private List<Ticket> listaTickets;

    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historila_ticket_socio);

        //Prueba
        valor = getIntent().getStringExtra("nombre_socio");
        rvTicketsH = findViewById(R.id.rvTicketsHistroial);
        rvTicketsH.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        AppDataBase db = Room.databaseBuilder(this,
                AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();
        // int id = db.clienteDao().returnNombreUsuario(valor);
        listaTickets = db.ticketDao().returnHistorialSocio(valor);
        rvTicketsH.setAdapter(new AdaptadorHistoricoSocioRecyclerView(listaTickets));




    }
}