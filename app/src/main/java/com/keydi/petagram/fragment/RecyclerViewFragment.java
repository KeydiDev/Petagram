package com.keydi.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keydi.petagram.R;
import com.keydi.petagram.adapter.MascotaAdapter;
import com.keydi.petagram.pojo.Mascota;
import com.keydi.petagram.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private RecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recycleview, container, false);

        listaMascotas = v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        listaMascotas.setLayoutManager(llm); // el recycle se comporte como LinearLayout
    }

    @Override
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdapter adapter = new MascotaAdapter(mascotas, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdapter adapter) {
        listaMascotas.setAdapter(adapter);
    }
}
