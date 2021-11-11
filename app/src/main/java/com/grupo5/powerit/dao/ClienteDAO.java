package com.grupo5.powerit.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.grupo5.powerit.entities.Cliente;

import java.util.List;

@Dao
public interface ClienteDAO {

    @Query(" SELECT * FROM cliente ")
    List<Cliente> getAll();

    @Query("SELECT nombreCompletoCliente FROM cliente WHERE nombreUsuario LIKE :nombreUsuario LIMIT 1")
    String findByNombreUsuario(String nombreUsuario);

    @Query("SELECT * FROM cliente WHERE nombreUsuario LIKE :nombreUsuario LIMIT 1")
    boolean findByNombreUsuarioConfirmacion(String nombreUsuario);

    @Query("SELECT * FROM cliente WHERE nombreUsuario LIKE :nombreUsuario AND contrasenia LIKE :contraUsuario ")
    boolean login(String nombreUsuario, String contraUsuario);

    @Query("SELECT idCliente FROM Cliente WHERE nombreUsuario LIKE :nombreUsuario")
    int returnNombreUsuario(String nombreUsuario);

    @Query( "SELECT telefono FROM Cliente WHERE nombreUsuario = :nombreUsuario" )
    String telefonoUsuario(String nombreUsuario);



    @Insert
    Long insert(Cliente cliente);

}
