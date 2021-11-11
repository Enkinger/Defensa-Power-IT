package com.grupo5.powerit.entities;


import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Ticket {

    @PrimaryKey(autoGenerate = true)
    int idTicket;

    @ColumnInfo(name = "Nombre_Socio")
    String nombreSocio;

    @ColumnInfo(name = "cliente")
    String cliente;

    @ColumnInfo(name = "Fecha")
    String fecha;

    @ColumnInfo(name = "Asunto")
    String asunto;

    @ColumnInfo(name = "Descripcion")
    String descripcion;

    @ColumnInfo(name = "Estado")
    String estado;

    @ColumnInfo(name = "imagenUrl")
    String imagenUrl;

 //CONSTUCTOR


    public Ticket(String cliente, String fecha, String asunto, String descripcion, String estado, String imagenUrl) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.imagenUrl = imagenUrl;
        this.nombreSocio = "";

    }

    //Gettes and setters


    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
