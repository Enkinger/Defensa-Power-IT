package com.grupo5.powerit.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"nombreUsuSocio"},
        unique = true)})
public class Socio {

    @PrimaryKey(autoGenerate = true)
    int idSocio;

    @ColumnInfo(name = "nombreCompleto")
    String nombreCompleto;

    @ColumnInfo(name = "emailSocio")
    String emailSocio;

    @ColumnInfo(name = "nombreUsuSocio")
    String nombreUsuarioS;

    @ColumnInfo(name = "contrasenia")
    String contrasenia;

    @ColumnInfo(name = "especialidad")
    String especialidad;

    public Socio(String nombreCompleto, String emailSocio, String nombreUsuarioS, String contrasenia, String especialidad) {
        this.nombreCompleto = nombreCompleto;
        this.emailSocio = emailSocio;
        this.nombreUsuarioS = nombreUsuarioS;
        this.contrasenia = contrasenia;
        this.especialidad = especialidad;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmailSocio() {
        return emailSocio;
    }

    public void setEmailSocio(String emailSocio) {
        this.emailSocio = emailSocio;
    }

    public String getNombreUsuarioS() {
        return nombreUsuarioS;
    }

    public void setNombreUsuarioS(String nombreUsuarioS) {
        this.nombreUsuarioS = nombreUsuarioS;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
