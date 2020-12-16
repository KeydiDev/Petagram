package com.keydi.petagram.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keydi.petagram.R;
import com.keydi.petagram.adapter.GridAdapter;
import com.keydi.petagram.adapter.MascotaAdapter;
import com.keydi.petagram.pojo.Mascota;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaMascotas = v.findViewById(R.id.rvGrid);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        listaMascotas.setLayoutManager(glm); // el recycle se comporte como LinearLayout
        inicializarListaMascota();
        inicializarAdaptador();

        return v;
    }
    public void inicializarAdaptador(){
        GridAdapter adapter = new GridAdapter(mascotas, getActivity());
        listaMascotas.setAdapter(adapter);
    }

    public void inicializarListaMascota(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("1", 4, R.drawable.cachorro));
        mascotas.add(new Mascota("2", 3, R.drawable.mascota1));
        mascotas.add(new Mascota("3", 5, R.drawable.mascota2));
        mascotas.add(new Mascota("4", 2, R.drawable.mascota1));
        mascotas.add(new Mascota("5", 1, R.drawable.mascota2));
        mascotas.add(new Mascota("4", 8, R.drawable.cachorro));
        mascotas.add(new Mascota("2", 3, R.drawable.mascota1));
        mascotas.add(new Mascota("8", 5, R.drawable.mascota2));
        mascotas.add(new Mascota("3", 2, R.drawable.mascota1));
        mascotas.add(new Mascota("7", 9, R.drawable.mascota2));
        mascotas.add(new Mascota("4", 1, R.drawable.cachorro));
        mascotas.add(new Mascota("2", 10, R.drawable.mascota1));
    }
}