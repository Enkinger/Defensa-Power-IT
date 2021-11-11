package com.grupo5.powerit.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.grupo5.powerit.R;
import com.grupo5.powerit.database.AppDataBase;

public class Login extends AppCompatActivity {
    private EditText nombreUsuario;
    private EditText contra;
    private TextView tvRegistrarse;
    private TextView tv_olvido_contrasena;
    private Button btn_iniciar_sesion;
    String url = "https://d500.epimg.net/cincodias/imagenes/2015/07/31/lifestyle/1438332173_646944_1438333610_sumario_normal.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_olvido_contrasena = findViewById(R.id.tv_olvido_contrasena);
        tv_olvido_contrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(url);
                Intent i = new Intent(Intent.ACTION_VIEW,link);
                startActivity(i);

            }
        });

        btn_iniciar_sesion = findViewById(R.id.btnIniciarSesion);
        btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BASE

                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();

                nombreUsuario = findViewById(R.id.eTextNombreUsu);
                contra = findViewById(R.id.eTextContra);

                String usuario = nombreUsuario.getText().toString();
                String password = contra.getText().toString();

                if(db.clienteDao().login(usuario, password)){
                    startActivity(new Intent(getApplicationContext(), Inicio_cliente.class).putExtra
                            ("nombre_usuario", usuario));

                }
                else if(db.soscioDAO().login(usuario,password)){

                    startActivity(new Intent(getApplicationContext(), Inicio_Socio.class).putExtra("nombre_socio", usuario));
                }
                else{

                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();

                }


            }
        });

        tvRegistrarse = findViewById(R.id.tvRegistrarse);
        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistroConTabLayout.class));
            }
        });


    }

}