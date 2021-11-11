package com.grupo5.powerit.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.grupo5.powerit.entities.Ticket;

import java.util.List;

@Dao
public interface TicketDao {
    @Query("SELECT * FROM Ticket")
    List<Ticket> getALL();

    @Insert
    Long insert(Ticket ticket);

    @Update
    void update(Ticket ticket);

    @Delete
    void delete(Ticket ticket);

    @Query("SELECT * FROM Ticket WHERE asunto like:asunto")
    Ticket findByAsunto(String asunto);

    @Query("SELECT * FROM Ticket where idTicket = :idTicket")
    Ticket findById(int idTicket);

    @Query("SELECT * FROM TickeT WHERE IdTicket = :idCliente")
    List<Ticket> returnDatos(int idCliente);

    //Ticket activos cliente
    @Query("SELECT * FROM Ticket WHERE cliente =:nombreUsuario AND Estado in('En proceso','En espera') order by fecha desc ")
    List<Ticket> returnDatosPrueba(String nombreUsuario);

    @Query("SELECT * FROM Ticket WHERE estado = 'En espera' order by fecha desc")
    List<Ticket> returnticketdisponibles();

    @Query( "SELECT Nombre_Socio FROM ticket WHERE idTicket =:idTicket" )
    String findsocioticket (int idTicket);

    @Query("UPDATE ticket SET Nombre_Socio = :nombreSocio, Estado = :estado WHERE idTicket = :ticket")
    void actualizarSocio(String nombreSocio, String estado,int ticket );

    //Finalizar Ticket
    @Query("UPDATE ticket SET  Estado = :estado WHERE idTicket = :ticket")
    int finalizarTicket( String estado,int ticket );

    //Historial ticket cliente:
    @Query("SELECT * FROM Ticket WHERE cliente =:nombreUsuario AND Estado = 'Finalizado' order by fecha desc")
    List<Ticket> returnHistorialCliente(String nombreUsuario);

    //Historial ticket cliente:
    @Query("SELECT * FROM Ticket WHERE Nombre_Socio =:nombreUsuario AND Estado = 'Finalizado' order by fecha desc")
    List<Ticket> returnHistorialSocio(String nombreUsuario);







    @Query("SELECT * FROM Ticket WHERE estado = 'En proceso' AND Nombre_Socio=:nombreUsuario order by fecha desc")
    List<Ticket> returnTicketsActivosSocio(String nombreUsuario);
}
