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

public class TicketsActivosSocio extends AppCompatActivity {

    private List<Ticket> listaTickets;
    private RecyclerView rvTicketsactivosSocio;
    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_activos_socio);

        valor = getIntent().getStringExtra("nombre_socio");
        rvTicketsactivosSocio = findViewById(R.id.rvTicketsActivosSocio);
        rvTicketsactivosSocio.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        AppDataBase db = Room.databaseBuilder(this,
                AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();

        listaTickets = db.ticketDao().returnTicketsActivosSocio(valor);
        rvTicketsactivosSocio.setAdapter(new AdaptadorTicketsActivosSocioRecycler(listaTickets));
    }

    public void onResume(){
        super.onResume();

        AppDataBase db = Room.databaseBuilder(this,
                AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();

        listaTickets = db.ticketDao().returnTicketsActivosSocio(valor);
        rvTicketsactivosSocio.setAdapter(new AdaptadorTicketsActivosSocioRecycler(listaTickets));

    }
}