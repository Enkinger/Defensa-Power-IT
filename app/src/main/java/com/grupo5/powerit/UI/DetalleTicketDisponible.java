package com.grupo5.powerit.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo5.powerit.R;
import com.grupo5.powerit.database.AppDataBase;

public class DetalleTicketDisponible extends AppCompatActivity {

    AppDataBase db;
    TextView tvAsunto, tvCliente, tvFecha,tvDescripcion;
    ImageView imgFoto;
    Button btnTicket;
    String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalle_ticket_disponible );
        valor = getIntent().getStringExtra("nombre_socio");

        Intent intentReceiverd = getIntent();
        Bundle parametros = intentReceiverd.getExtras();
        String asunto = parametros.getString( "asunto" );
        String cliente = parametros.getString( "cliente" );
        String nombresocio = parametros.getString( "nombre_socio" );
        String fecha = parametros.getString( "fecha" );
        String descripcion = parametros.getString( "descripcion" );
        String url = parametros.getString( "imagen" );
        int idT = parametros.getInt("id");

        tvAsunto = findViewById( R.id.tv_ticket );
        tvCliente = findViewById( R.id.tv_cliente );
        tvFecha = findViewById( R.id.tv_fecha );
        tvDescripcion = findViewById( R.id.tv_descripcion );
        imgFoto = findViewById( R.id.imgView_foto );
        btnTicket = findViewById(R.id.btnticket);

        tvAsunto.setText( asunto );
        tvCliente.setText( cliente );
        tvFecha.setText( fecha );
        tvDescripcion.setText( descripcion );

        Bitmap urlimagen = BitmapFactory.decodeFile( url );
        imgFoto.setImageBitmap( urlimagen );

        btnTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();

                db.ticketDao().actualizarSocio(valor, "En proceso",idT);
                Toast.makeText(DetalleTicketDisponible.this, "Socio: " + valor, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }
}