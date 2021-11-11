package com.grupo5.powerit.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.grupo5.powerit.R;
import com.grupo5.powerit.dao.TicketDao;
import com.grupo5.powerit.database.AppDataBase;

public class DetalleTicketsCliente extends AppCompatActivity {

    AppDataBase db;
    TextView tvAsuntoTicketActivo, tvSocioTicketActivo, tvFechaTicketActivo,tvDescripcionTicketActivo,tvEstadoTicketActivo;
    ImageView imgFotoTicketActivo;
    String nombresocio;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalle_ticket_cliente );

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
        String ususocio = parametros.getString( "socio" );
        int idT = parametros.getInt("id");

        nombresocio = db.soscioDAO().nombresocioticket( ususocio );

        tvAsuntoTicketActivo = findViewById( R.id.tv_ticket_ticket_activo_Cli );
        tvSocioTicketActivo = findViewById( R.id.tv_socio_ticket_activo_Cli );
        tvFechaTicketActivo = findViewById( R.id.tv_fecha_ticket_activo_Cli );
        tvDescripcionTicketActivo = findViewById( R.id.tv_descripcion_ticket_activo_Cli );
        imgFotoTicketActivo = findViewById( R.id.imgView_foto_ticket_activo_Cli );
        tvEstadoTicketActivo = findViewById( R.id.tv_estado_ticket_activo_Cli );

        tvAsuntoTicketActivo.setText( asunto );
        tvSocioTicketActivo.setText( cliente );
        tvFechaTicketActivo.setText( fecha );
        tvDescripcionTicketActivo.setText( descripcion );
        tvEstadoTicketActivo.setText( estado );
        tvSocioTicketActivo.setText( nombresocio );

        Bitmap urlimagen = BitmapFactory.decodeFile( url );
        imgFotoTicketActivo.setImageBitmap( urlimagen );

    }
}

