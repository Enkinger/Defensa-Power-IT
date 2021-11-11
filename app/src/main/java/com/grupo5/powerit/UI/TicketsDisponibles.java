package com.grupo5.powerit.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.grupo5.powerit.R;
import com.grupo5.powerit.database.AppDataBase;
import com.grupo5.powerit.entities.Ticket;

import java.util.List;

public class TicketsDisponibles extends AppCompatActivity {

    private RecyclerView rvTicketsDisponibles;
    private List<Ticket> listaTicketsDisponibles;
    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tickets_disponibles );

        valor = getIntent().getStringExtra("nombre_socio");

        rvTicketsDisponibles = findViewById(R.id.rvTicketsDisponibles);
        rvTicketsDisponibles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));




        AppDataBase db = Room.databaseBuilder(this,
                AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();
        //listaTickets = db.ticketDao().returnDatosPrueba(valor);
        listaTicketsDisponibles = db.ticketDao().returnticketdisponibles();
        rvTicketsDisponibles.setAdapter(new AdaptadorDisponibleRecyclerView( listaTicketsDisponibles, valor));

    }

    public void onResume(){
        super.onResume();

        AppDataBase db = Room.databaseBuilder(this,
                AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();
        //listaTickets = db.ticketDao().returnDatosPrueba(valor);
        listaTicketsDisponibles = db.ticketDao().returnticketdisponibles();
        rvTicketsDisponibles.setAdapter(new AdaptadorDisponibleRecyclerView( listaTicketsDisponibles, valor));

    }
}