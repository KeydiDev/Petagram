package com.keydi.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.keydi.petagram.adapter.MascotaAdapter;
import com.keydi.petagram.db.BaseDatos;
import com.keydi.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.List;

public class Favoritas extends AppCompatActivity {

    List<Mascota> mascotasFavoritas;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);

        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = findViewById(R.id.rvFavoritos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        listaMascotas.setLayoutManager(llm); // el recycle se comporte como LinearLayout
        inicializarListaMascota();
        inicializarAdaptador();
    }

    public void inicializarAdaptador(){
        MascotaAdapter adapter = new MascotaAdapter(mascotasFavoritas, this);
        listaMascotas.setAdapter(adapter);
    }

    public void inicializarListaMascota(){
        BaseDatos db = new BaseDatos(this);
        mascotasFavoritas = db.obtenerTodasLasFavoritas();

    }
}