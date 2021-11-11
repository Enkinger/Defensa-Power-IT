package com.grupo5.powerit.UI;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo5.powerit.R;
import com.grupo5.powerit.entities.Ticket;

import java.util.List;

public class AdaptadorDisponibleRecyclerView extends RecyclerView.Adapter<AdaptadorDisponibleRecyclerView.ViewHolderDatos>{

    private List<Ticket> listaTicketsDisponibles;
    private String nombreSocio;
    
    public AdaptadorDisponibleRecyclerView(List<Ticket> listaTicketsDisponibles, String nombreSocio) {
        this.listaTicketsDisponibles = listaTicketsDisponibles;
        this.nombreSocio = nombreSocio;

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.modelo_tickets_disponibles, parent, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaTicketsDisponibles.get(position));
        holder.dato= nombreSocio;

    }

    @Override
    public int getItemCount() {
        return listaTicketsDisponibles.size();
    }

    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tvFechaTicket, tvAsuntoTicket, tvClienteTicket;
        String DescripcionTicket, UrlImagen;
        int idTicket;
        Button BtnMas;
        String dato;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            tvFechaTicket = itemView.findViewById( R.id.tvFechaTicket);
            tvAsuntoTicket = itemView.findViewById( R.id.tvAsuntoTicketActivo );
            tvClienteTicket = itemView.findViewById( R.id.tvClienteTicket );
            BtnMas = itemView.findViewById( R.id.btn_mas_ticketSocio);

            BtnMas.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(),DetalleTicketDisponible.class);
                    intent.putExtra("nombre_socio", dato);
                    intent.putExtra( "asunto",tvAsuntoTicket.getText());
                    intent.putExtra( "cliente",tvClienteTicket.getText());
                    intent.putExtra( "fecha",tvFechaTicket.getText());
                    intent.putExtra( "descripcion", DescripcionTicket );
                    intent.putExtra( "imagen",UrlImagen );
                    intent.putExtra("id",idTicket);
                    itemView.getContext().startActivity( intent );
                }
            } );
        }



        public void asignarDatos(Ticket ticket) {
            tvFechaTicket.setText(ticket.getFecha().toString());
            tvAsuntoTicket.setText(ticket.getAsunto());
            tvClienteTicket.setText( ticket.getCliente() );
            DescripcionTicket = ticket.getDescripcion();
            UrlImagen = ticket.getImagenUrl();
            idTicket = ticket.getIdTicket();
        }

    }
}
