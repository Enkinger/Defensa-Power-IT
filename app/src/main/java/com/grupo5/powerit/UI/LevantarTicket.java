package com.grupo5.powerit.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.room.Room;

import com.grupo5.powerit.R;
import com.grupo5.powerit.database.AppDataBase;
import com.grupo5.powerit.entities.Ticket;

import org.xml.sax.ext.DeclHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LevantarTicket extends AppCompatActivity {


    Button btnCamara,btnInsertar;
    ImageView imgView;
    String rutaImagen;
    private EditText etNombreUsuario,asunto,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_levantar_ticket );

        //Conecto con la vista
        btnCamara = findViewById(R.id.btnCamara);
        imgView = findViewById(R.id.imageViewFoto);
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        btnInsertar = findViewById(R.id.btnSubirTicket);
        asunto = findViewById(R.id.etAsunto);
        descripcion = findViewById(R.id.etDescripcion);

        //Obtengo el nombre de Usuario del clinte loegeado
        String valor = getIntent().getStringExtra("nombre_usuario");
        etNombreUsuario.setText(valor);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });

        //Boton insertat

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();

                String nombreUsuario = etNombreUsuario.getText().toString();
                String asuntoTicket = asunto.getText().toString();
                String descripcionTicket = descripcion.getText().toString();
                String rutaimagen = rutaImagen;

                //Fecha
                String fecha = obtenerFecha();
                //Estado
                String estado = "En espera";


                Ticket ticket = new Ticket(nombreUsuario,fecha,asuntoTicket,descripcionTicket,estado,rutaimagen);

                Long ref = db.ticketDao().insert(ticket);


                if ((ref > 0)){

                    Toast.makeText(getApplicationContext(),"Ticket creado correctamente",Toast.LENGTH_LONG).show();
                    //volver a al menu principal
                    onBackPressed();
                }else {
                    Toast.makeText(getApplicationContext(),"Fallo al registar",Toast.LENGTH_LONG).show();
                }




            }
        });










    }

    private void abrirCamara(){
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){

            File imagenArchivo = null;

            try {
                imagenArchivo = crearImagen();
            } catch (IOException ex){
                Log.e("Error", ex.toString());
            }

            if(imagenArchivo != null){
                Uri fotoUri = FileProvider.getUriForFile(this,"com.grupo5.powerit.fileprovider", imagenArchivo);
                intent.putExtra( MediaStore.EXTRA_OUTPUT,fotoUri );
                startActivityForResult(intent, 1);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap imgBitmap = BitmapFactory.decodeFile( rutaImagen );
            imgView.setImageBitmap(imgBitmap);
        }
    }

    private File crearImagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir( Environment.DIRECTORY_PICTURES );
        File imagen = File.createTempFile( nombreImagen, ".jpg", directorio );

        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }

    //Metodo para obtener la hora
    public String obtenerFecha(){

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);




        return thisDate;
    }


}