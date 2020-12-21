package com.keydi.petagram.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.keydi.petagram.db.ConstructorMascotas;
import com.keydi.petagram.pojo.Mascota;
import com.keydi.petagram.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MascotaAdapter extends  RecyclerView.Adapter<MascotaAdapter.ContactoViewHolder>{

    List<Mascota> mascotas;
    Activity activity;

    public MascotaAdapter(List<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override //Inflar el layout y lo psara al viewHolder para el obtenga views
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override // asocia cada elemento de la lista con cada view
    public void onBindViewHolder(@NonNull final ContactoViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        final ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombre.setText(mascota.getNombre());
        holder.tvRaiting.setText("" +constructorMascotas.obtenerLikesMascota(mascota));

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constructorMascotas.darLikeMascota(mascota);
                holder.tvRaiting.setText("" +constructorMascotas.obtenerLikesMascota(mascota));
            }
        });
    }

    @Override
    public int getItemCount() { // cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvRaiting;
        private ImageButton btnLike;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoto     = itemView.findViewById(R.id.imgFoto);
            tvNombre    = itemView.findViewById(R.id.tvNombre);
            tvRaiting    = itemView.findViewById(R.id.tvRaiting);
            btnLike     = itemView.findViewById(R.id.button_bone);
        }
    }
}
