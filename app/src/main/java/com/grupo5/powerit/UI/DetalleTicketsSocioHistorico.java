package com.grupo5.powerit.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.grupo5.powerit.R;
import com.grupo5.powerit.database.AppDataBase;

public class DetalleTicketsSocioHistorico extends AppCompatActivity {


    TextView tvAsuntoTicketHistorico,tvSocioTicketHistorico,tvFechaTicketHistorico,tvDescripcionTicketHistorico,tvEstadoTicketHistorico;
    ImageView imgFotoTicketHistorico;
    String nombrecliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalle_tickets_socio_historico );

        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();



        Intent intentReceiverd = getIntent();
        Bundle parametros = intentReceiverd.getExtras();
        String asunto = parametros.getString( "asunto" );
        String fecha = parametros.getString( "fecha" );
        String cliente = parametros.getString( "cliente" );
        String descripcion = parametros.getString( "descripcion" );
        String estado = parametros.getString( "estado" );
        String url = parametros.getString( "imagen" );
        int idT = parametros.getInt("id");

        nombrecliente = db.clienteDao().findByNombreUsuario( cliente );

        tvAsuntoTicketHistorico = findViewById( R.id.tv_ticket_ticket_historico_Socio );
        tvSocioTicketHistorico = findViewById( R.id.tv_cliente_ticket_historico_Socio );
        tvFechaTicketHistorico = findViewById( R.id.tv_fecha_ticket_historico_Socio );
        tvDescripcionTicketHistorico = findViewById( R.id.tv_descripcion_ticket_historico_Socio );
        imgFotoTicketHistorico = findViewById( R.id.imgView_foto_ticket_historico_Socio );
        tvEstadoTicketHistorico = findViewById( R.id.tv_estado_ticket_historico_Socio );

        tvAsuntoTicketHistorico.setText( asunto );
        tvSocioTicketHistorico.setText( cliente );
        tvFechaTicketHistorico.setText( fecha );
        tvDescripcionTicketHistorico.setText( descripcion );
        tvEstadoTicketHistorico.setText( estado );
        tvSocioTicketHistorico.setText( nombrecliente );

        Bitmap urlimagen = BitmapFactory.decodeFile( url );
        imgFotoTicketHistorico.setImageBitmap( urlimagen );

    }
}