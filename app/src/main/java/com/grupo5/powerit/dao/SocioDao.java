package com.grupo5.powerit.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.grupo5.powerit.entities.Socio;

import java.util.List;

@Dao
public interface SocioDao {

    @Query("SELECT * FROM Socio ")
    List<Socio> getAll();

    @Insert
    Long insertSocio(Socio socio);

    @Query("SELECT * FROM Socio WHERE nombreUsuSocio LIKE :nombreUsuario LIMIT 1")
    boolean findByNombreUsuarioConfirmacion(String nombreUsuario);

    @Query("SELECT * FROM Socio WHERE nombreUsuSocio LIKE :nombreUsuario AND contrasenia LIKE :contraUsuario ")
    boolean login(String nombreUsuario, String contraUsuario);

    @Query("SELECT nombreCompleto FROM Socio WHERE nombreUsuSocio = :ticketsocio")
    String nombresocioticket(String ticketsocio);

}