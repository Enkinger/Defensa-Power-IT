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

public class AdaptadorTicketsActivosSocioRecycler extends RecyclerView.Adapter<AdaptadorTicketsActivosSocioRecycler.ViewHolderActivosTickets> {

    List<Ticket> listaTickets;

    public AdaptadorTicketsActivosSocioRecycler(List<Ticket> listaTickets) {
        this.listaTickets = listaTickets;
    }

    @NonNull
    @Override
    public AdaptadorTicketsActivosSocioRecycler.ViewHolderActivosTickets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_tickets_activos_socio, parent, false);

        return new AdaptadorTicketsActivosSocioRecycler.ViewHolderActivosTickets(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderActivosTickets holder, int position) {
        holder.asignarDatos(listaTickets.get(position));

    }

    @Override
    public int getItemCount() {
        return listaTickets.size();
    }

    public static class ViewHolderActivosTickets extends RecyclerView.ViewHolder {

        TextView tvAsuntoTicketActivo,tvFechaTicketActivo,tvClienteTicketActivo;
        String EstadoTicketActivo, DescripcionTicketActivo, UrlImagen, SocioTicketActivo;
        Button btnDetalles;
        int idTicketActivo;

        public ViewHolderActivosTickets(@NonNull View itemView) {
            super(itemView);

            tvFechaTicketActivo =  itemView.findViewById( R.id.tvFechaTicketActivo );
            tvAsuntoTicketActivo = itemView.findViewById(R.id.tvAsuntoTicketActivo );
            tvClienteTicketActivo = itemView.findViewById(R.id.tvClienteTicketActivo );

            btnDetalles = itemView.findViewById(R.id.btn_mas_ticketSocio);
            btnDetalles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(),DetalleTicketsSocios.class);
                    intent.putExtra( "asunto",tvAsuntoTicketActivo.getText());
                    intent.putExtra( "cliente",tvClienteTicketActivo.getText());
                    intent.putExtra( "fecha", tvFechaTicketActivo.getText());
                    intent.putExtra("nombre_socio", SocioTicketActivo);
                    intent.putExtra( "descripcion", DescripcionTicketActivo );
                    intent.putExtra( "estado", EstadoTicketActivo );
                    intent.putExtra( "imagen",UrlImagen );
                    intent.putExtra("id",idTicketActivo);
                    itemView.getContext().startActivity( intent );
                }
            });
        }

        public void asignarDatos(Ticket ticket) {
            tvAsuntoTicketActivo.setText(ticket.getAsunto());
            tvClienteTicketActivo.setText(ticket.getCliente());
            tvFechaTicketActivo.setText(ticket.getFecha().toString());
            SocioTicketActivo = ticket.getNombreSocio();
            EstadoTicketActivo = ticket.getEstado();
            DescripcionTicketActivo = ticket.getDescripcion();
            UrlImagen = ticket.getImagenUrl();
            idTicketActivo = ticket.getIdTicket();
        }
    }
}
