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

public class AdaptadorHistoricoSocioRecyclerView extends RecyclerView.Adapter<AdaptadorHistoricoSocioRecyclerView.ViewHolderDatos>{

    private List<Ticket> listaTickets;

    public AdaptadorHistoricoSocioRecyclerView(List<Ticket> listaTickets) {
        this.listaTickets = listaTickets;
    }

    @NonNull
    @Override
    public AdaptadorHistoricoSocioRecyclerView.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_tickets_activos, parent, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorHistoricoSocioRecyclerView.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaTickets.get(position));
    }

    @Override
    public int getItemCount() {
        return listaTickets.size();
    }

    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tvFechaTicket, tvAsuntoTicket;
        String DescripcionTicket, UrlImagen, EstadoTicket, ClienteTicket;
        int idTicket;
        Button BtnMas;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            tvFechaTicket = itemView.findViewById(R.id.tvFechaTicketActivoCli);
            tvAsuntoTicket = itemView.findViewById(R.id.tvAsuntoTicketActivoActivoCli );
            BtnMas = itemView.findViewById( R.id.btn_mas_ticketCliente );

            BtnMas.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(),DetalleTicketsSocioHistorico.class);

                    intent.putExtra( "asunto",tvAsuntoTicket.getText());
                    intent.putExtra( "fecha",tvFechaTicket.getText());
                    intent.putExtra( "descripcion", DescripcionTicket );
                    intent.putExtra( "estado", EstadoTicket );
                    intent.putExtra( "imagen",UrlImagen );
                    intent.putExtra("id",idTicket);
                    intent.putExtra("cliente",ClienteTicket);
                    itemView.getContext().startActivity( intent );
                }
            } );

        }

        public void asignarDatos(Ticket ticket) {
            tvFechaTicket.setText(ticket.getFecha().toString());
            tvAsuntoTicket.setText(ticket.getAsunto());
            EstadoTicket = ticket.getEstado();
            DescripcionTicket = ticket.getDescripcion();
            UrlImagen = ticket.getImagenUrl();
            idTicket = ticket.getIdTicket();
            ClienteTicket = ticket.getCliente();
        }
    }
}
