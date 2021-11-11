package com.grupo5.powerit;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.grupo5.powerit.dao.TicketDao;
import com.grupo5.powerit.database.AppDataBase;
import com.grupo5.powerit.entities.Ticket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class TicketTest {

    private TicketDao ticketDao;
    private AppDataBase appDataBase;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        appDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).build();
        ticketDao = appDataBase.ticketDao();
    }

    @After
    public void closeDB() throws IOException {
        appDataBase.close();
    }

    @Test
    public void findByAsuntoTest() throws IOException {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1);
        ticket.setAsunto("No me funciona PC");

        ticketDao.insert(ticket);

        Ticket buscado = ticketDao.findByAsunto("No me funciona PC");

        assertTrue("No encontro el asunto", ticket.getIdTicket() == buscado.getIdTicket());
    }
}