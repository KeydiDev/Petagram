package com.keydi.petagram.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.keydi.petagram.R;
import com.keydi.petagram.pojo.Mascota;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridAdapter extends  RecyclerView.Adapter<GridAdapter.ContactoViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;

    public GridAdapter(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override //Inflar el layout y lo psara al viewHolder para el obtenga views
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_mascota, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override // asocia cada elemento de la lista con cada view
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvRaiting.setText("" +mascota.getRaiting());
    }

    @Override
    public int getItemCount() { // cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvRaiting;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoto     = itemView.findViewById(R.id.imgFotoGrid);
            tvRaiting    = itemView.findViewById(R.id.tvRatGrid);
        }
    }
}
