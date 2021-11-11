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

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.grupo5.powerit.R;
import com.grupo5.powerit.database.AppDataBase;
import com.grupo5.powerit.entities.Cliente;

public class FragmentCliente extends Fragment {

    EditText etNombreCompleto, etNombreUsuario, etContra, etContra2, etEmail, etTelefono;
    Button btnRegistro;


    public FragmentCliente() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        //Conecto los botones con la vista
        etNombreCompleto = view.findViewById(R.id.eTexNombreCompC);
        etNombreUsuario = view.findViewById(R.id.eTexNombreUsuC);
        etContra = view.findViewById(R.id.eTextContraseniaC);
        etContra2 = view.findViewById(R.id.eTexCompContaseniaC);
        etEmail = view.findViewById(R.id.eTexEmailC);
        etTelefono = view.findViewById(R.id.eTextTelefonoC);
        btnRegistro = view.findViewById(R.id.btnRegistrarseC);

      //Boton Inicar sesion
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
                String email = etEmail.getText().toString();
                String password = etContra.getText().toString();
                String password2 = etContra2.getText().toString();
                String telefono = etTelefono.getText().toString();
                
                if (nombreCompleto.isEmpty() || nombreUsuario.isEmpty() || email.isEmpty() || password.isEmpty() || telefono.isEmpty() || password2.isEmpty()){

                    Toast.makeText(thiscontext, "Uno o mas campos estan vacíos", Toast.LENGTH_SHORT).show();

                 }else if (db.clienteDao().findByNombreUsuarioConfirmacion(nombreUsuario)){

                    Toast.makeText(thiscontext, "Elija otro nombre de usuario", Toast.LENGTH_SHORT).show();

                }else if (!password2.equals(password)){

                    etContra2.setError("Confirmación incorrecta");

                }else {

                    Cliente cliente = new Cliente(nombreCompleto,nombreUsuario,email,password,telefono);

                    Long reg = db.clienteDao().insert(cliente);

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