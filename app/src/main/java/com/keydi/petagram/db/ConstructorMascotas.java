package com.keydi.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.keydi.petagram.R;
import com.keydi.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.List;

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        BaseDatos db = new BaseDatos(context);
        mascotas = db.obtenerTodasLasMascotas();

        if (mascotas == null || mascotas.isEmpty()){
            insertarMascotas(db);
            mascotas = db.obtenerTodasLasMascotas();
        }

        return mascotas;
    }

    public void insertarMascotas(BaseDatos db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "bob");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.cachorro);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "pluto");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota1);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "kitty");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota2);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "pakito");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "tico");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota1);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "yita");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota1);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "choco");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota2);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "trajeado");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perrito);

        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);

        List<Mascota> mascotas = db.obtenerTodasLasFavoritas();
        List<Integer> ids = new ArrayList<>();
        boolean yaEsta = false;
        for (Mascota m : mascotas){
            ids.add(m.getId());
            if (mascota.getId() == m.getId()){
                yaEsta = true;
                break;
            }
        }
        if (!yaEsta){
            int count = ids.size();
            ContentValues contentValuesFav  = new ContentValues();
            contentValuesFav.put(ConstantesBaseDatos.TABLE_FAVORITOS_MASCOTA_ID, mascota.getId());

            if (count < 5){
                db.insertarFavorito(contentValuesFav);
            } else {
                db.deleteAndCreateFavoritos();
                db.insertarFavorito(contentValuesFav);

                for (int i = 0; i < 4; i++){
                    contentValuesFav = new ContentValues();
                    contentValuesFav.put(ConstantesBaseDatos.TABLE_FAVORITOS_MASCOTA_ID, mascotas.get(i).getId());
                    db.insertarFavorito(contentValuesFav);
                }
            }
        }
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerLikesMascota(mascota);
    }
}
