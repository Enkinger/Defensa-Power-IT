package com.grupo5.powerit.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.grupo5.powerit.R;
import com.grupo5.powerit.database.AppDataBase;
import com.grupo5.powerit.entities.Socio;

public class FragmentSocio extends Fragment {

    EditText etNombreCompleto, etNombreUsuario, etContra, etContra2, etEmail, etEspecialidad;
    Button btnRegistro;

    public FragmentSocio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_socio, container, false);

        //Conecto los botones con la vista
        etNombreCompleto = view.findViewById(R.id.etNombreCompleto);
        etNombreUsuario = view.findViewById(R.id.etNombreUsuario);
        etContra = view.findViewById(R.id.etContraS);
        etContra2 = view.findViewById(R.id.etConContraS);
        etEmail = view.findViewById(R.id.etEmail);
        etEspecialidad = view.findViewById(R.id.etEspecialidad);
        btnRegistro = view.findViewById(R.id.btnRegistrarseC);


        View tvIniciarSesion = view.findViewById(R.id.tvIniciarSesion);
        tvIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Login.class));
            }
        });

      Context thiscontext = container.getContext();
        //Boton registro

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase db = Room.databaseBuilder(thiscontext,
                        AppDataBase.class, "BaseDeDatosI").allowMainThreadQueries().build();

                String nombreCompleto = etNombreCompleto.getText().toString();
                String nombreUsuario = etNombreUsuario.getText().toString();
                String password = etContra.getText().toString();
                String password2 = etContra2.getText().toString();
                String especialidad = etEspecialidad.getText().toString();
                String email = etEmail.getText().toString();

                if (nombreCompleto.isEmpty() || nombreUsuario.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty() || especialidad.isEmpty()){

                    Toast.makeText(thiscontext, "Uno o mas campos estan vacíos", Toast.LENGTH_SHORT).show();

                }else if (db.soscioDAO().findByNombreUsuarioConfirmacion(nombreUsuario)){

                    Toast.makeText(thiscontext, "Elija otro nombre de usuario", Toast.LENGTH_SHORT).show();

                }else if (!password2.equals(password)) {

                    etContra2.setError("Confirmación incorrecta");

                }else {

                    Socio socio = new Socio(nombreCompleto,email,nombreUsuario,password,especialidad);

                    Long reg = db.soscioDAO().insertSocio(socio);

                    if ((reg > 0)){

                        Toast.makeText(thiscontext,"Registrado correctamente",Toast.LENGTH_LONG).show();
                        getActivity().onBackPressed();

                    }else {
                        Toast.makeText(thiscontext,"Fallo al registar",Toast.LENGTH_LONG).show();
                    }
                }

            }

        });

        return view;
    }
}