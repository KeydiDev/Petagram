package com.keydi.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity {

    ArrayList<Mascota> mascotasFavoritas;
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
        mascotasFavoritas = new ArrayList<Mascota>();


        mascotasFavoritas.add(new Mascota("pluto", 3, R.drawable.mascota1));
        mascotasFavoritas.add(new Mascota("kitty", 5, R.drawable.mascota2));
        mascotasFavoritas.add(new Mascota("bob", 1, R.drawable.cachorro));
        mascotasFavoritas.add(new Mascota("rocco", 2, R.drawable.mascota1));
        mascotasFavoritas.add(new Mascota("tico", 1, R.drawable.mascota2));

    }
}