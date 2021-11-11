package com.grupo5.powerit.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.grupo5.powerit.dao.ClienteDAO;
import com.grupo5.powerit.dao.SocioDao;
import com.grupo5.powerit.dao.TicketDao;
import com.grupo5.powerit.entities.Cliente;
import com.grupo5.powerit.entities.Socio;
import com.grupo5.powerit.entities.Ticket;

@Database(entities = {Cliente.class, Socio.class, Ticket.class}, version =1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ClienteDAO clienteDao();

    public abstract SocioDao soscioDAO();

    public abstract TicketDao ticketDao();
}
