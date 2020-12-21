package com.keydi.petagram.fragment;

import com.keydi.petagram.adapter.MascotaAdapter;
import com.keydi.petagram.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragment {

    public void generarLinearLayoutVertical();

    public MascotaAdapter crearAdaptador (ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdapter adaptador);

}
