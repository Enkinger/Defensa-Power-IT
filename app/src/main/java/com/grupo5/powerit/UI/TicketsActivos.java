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

public class TicketsActivos extends AppCompatActivity {

    private RecyclerView rvTicketsActivos;
    private List<Ticket> listaTickets;
    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_activos);

        valor = getIntent().getStringExtra("nombre_usuario");
        rvTicketsActivos = findViewById(R.id.rvTicketsHistroial);
        rvTicketsActivos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        AppDataBase db = Room.databaseBuilder(this,
                AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();
       // int id = db.clienteDao().returnNombreUsuario(valor);
        listaTickets = db.ticketDao().returnDatosPrueba(valor);
        rvTicketsActivos.setAdapter(new AdaptadorRecyclerView(listaTickets));




    }
}