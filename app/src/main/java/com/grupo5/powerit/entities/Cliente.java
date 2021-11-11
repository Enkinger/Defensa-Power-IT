package com.grupo5.powerit.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"nombreUsuario"},
        unique = true)})

public class Cliente {

    @PrimaryKey(autoGenerate = true)
    int idCliente;

    @ColumnInfo(name = "nombreCompletoCliente")
    String nombreCliente;

    @ColumnInfo(name = "nombreUsuario")
    String nombreUsuario;

    @ColumnInfo(name = "emailCliente")
    String emailCliente;



    @ColumnInfo(name = "contrasenia")
    String contrasenia;

    @ColumnInfo(name = "telefono")
    String telefono;



    public Cliente(String nombreCliente, String nombreUsuario, String emailCliente, String contrasenia, String telefono) {
        this.nombreCliente = nombreCliente;
        this.nombreUsuario = nombreUsuario;
        this.emailCliente = emailCliente;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }


}