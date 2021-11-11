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
import com.grupo5.powerit.database.AppDataBase;

public class DetalleTicketsSocios extends AppCompatActivity {

    AppDataBase db;
    TextView tvAsuntoTicketActivo, tvClienteTicketActivo, tvFechaTicketActivo,tvDescripcionTicketActivo,tvEstadoTicketActivo;
    ImageView imgFotoTicketActivo, imgWhatsapp;
    Button btnFinalizarTicket;
    String valor, telefonocliente;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalle_ticket_socio );
        valor = getIntent().getStringExtra("nombre_socio");

        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();



        Intent intentReceiverd = getIntent();
        Bundle parametros = intentReceiverd.getExtras();
        String asunto = parametros.getString( "asunto" );
        String cliente = parametros.getString( "cliente" );
        String fecha = parametros.getString( "fecha" );
        String descripcion = parametros.getString( "descripcion" );
        String estado = parametros.getString( "estado" );
        String url = parametros.getString( "imagen" );
        int idT = parametros.getInt("id");
        telefonocliente = db.clienteDao().telefonoUsuario( cliente );

        tvAsuntoTicketActivo = findViewById( R.id.tv_ticket_ticket_activo );
        tvClienteTicketActivo = findViewById( R.id.tv_cliente_ticket_activo );
        tvFechaTicketActivo = findViewById( R.id.tv_fecha_ticket_activo );
        tvDescripcionTicketActivo = findViewById( R.id.tv_descripcion_ticket_activo );
        imgFotoTicketActivo = findViewById( R.id.imgView_foto_ticket_activo );
        btnFinalizarTicket = findViewById(R.id.btnticket);
        tvEstadoTicketActivo = findViewById( R.id.tv_estado_ticket_activo );
        imgWhatsapp = findViewById( R.id.imageWhatsapp_ticket_activo );

        tvAsuntoTicketActivo.setText( asunto );
        tvClienteTicketActivo.setText( cliente );
        tvFechaTicketActivo.setText( fecha );
        tvDescripcionTicketActivo.setText( descripcion );
        tvEstadoTicketActivo.setText( estado );

        Bitmap urlimagen = BitmapFactory.decodeFile( url );
        imgFotoTicketActivo.setImageBitmap( urlimagen );

        imgWhatsapp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarWhatsApp();
            }
        } );


        btnFinalizarTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String estado = "Finalizado";
                int funciono = db.ticketDao().finalizarTicket(estado,idT);
               if(funciono > 0){
                   Toast.makeText(DetalleTicketsSocios.this, "Ticket finalizado con exito: ", Toast.LENGTH_SHORT).show();
               }else {

                   Toast.makeText(DetalleTicketsSocios.this, "Ocurrio un error al finalizar el ticket: ", Toast.LENGTH_SHORT).show();

               }
               //IR A MENU PRINCIPAL SOCIO
                onBackPressed();

            }
        });


    }




    public void enviarWhatsApp(){
        String numerocodif;
        String numero = telefonocliente;
        if (numero.length()==9){
            numerocodif = numero.substring(1);
        }else {
            numerocodif = numero;
        }
        String smsNumber = "598"+numerocodif; //without "+"
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData( Uri.parse("http://api.whatsapp.com/send?phone="+smsNumber+"&text="+"This is my text to send."));
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
