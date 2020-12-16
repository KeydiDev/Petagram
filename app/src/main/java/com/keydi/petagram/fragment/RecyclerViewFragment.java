package com.keydi.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keydi.petagram.R;
import com.keydi.petagram.adapter.MascotaAdapter;
import com.keydi.petagram.pojo.Mascota;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_recycleview, container, false);

        listaMascotas = v.findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        listaMascotas.setLayoutManager(llm); // el recycle se comporte como LinearLayout
        inicializarListaMascota();
        inicializarAdaptador();

        return v;
    }
    public void inicializarAdaptador(){
        MascotaAdapter adapter = new MascotaAdapter(mascotas, getActivity());
        listaMascotas.setAdapter(adapter);
    }

    public void inicializarListaMascota(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("bob", 1, R.drawable.cachorro));
        mascotas.add(new Mascota("pluto", 3, R.drawable.mascota1));
        mascotas.add(new Mascota("kitty", 5, R.drawable.mascota2));
        mascotas.add(new Mascota("rocco", 2, R.drawable.mascota1));
        mascotas.add(new Mascota("tico", 1, R.drawable.mascota2));

    }



}
