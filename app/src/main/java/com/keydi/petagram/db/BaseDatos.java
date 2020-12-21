package com.keydi.petagram.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.keydi.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;
    String queryCrearTablaFavoritos = "CREATE TABLE " + ConstantesBaseDatos.TABLE_FAVORITOS + "(" +
            ConstantesBaseDatos.TABLE_FAVORITOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ConstantesBaseDatos.TABLE_FAVORITOS_MASCOTA_ID + " INTEGER, " +
            "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_FAVORITOS_MASCOTA_ID + ") " +
            "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
            ")";

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE   + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RAITING  + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO     + " INTEGER" +
                ")";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
        db.execSQL(queryCrearTablaFavoritos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(new StringBuilder().append("DROP TABLE IF EXIST ").append(ConstantesBaseDatos.TABLE_MASCOTAS).toString());
        db.execSQL(new StringBuilder().append("DROP TABLE IF EXIST ").append(ConstantesBaseDatos.TABLE_LIKES_MASCOTA).toString());
        db.execSQL(new StringBuilder().append("DROP TABLE IF EXIST ").append(ConstantesBaseDatos.TABLE_FAVORITOS).toString());
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros =  db.rawQuery(query,null);
        while (registros.moveToNext()){
            Mascota mascotaAtual = new Mascota();
            mascotaAtual.setId(registros.getInt(0));
            mascotaAtual.setNombre(registros.getString(1));
            mascotaAtual.setRaiting(registros.getInt(2));
            mascotaAtual.setFoto(registros.getInt(3));

            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+")" +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascotaAtual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaAtual.setRaiting(registrosLikes.getInt(0));
            }else{
                mascotaAtual.setRaiting(0);
            }

            mascotas.add(mascotaAtual);

        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarFavorito(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_FAVORITOS, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT ( " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + ")"
                    + " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " = "+mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();

        return likes;
    }

    public Mascota obtenerMascota(int id){

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS +
                " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        Mascota mascotaAtual = null;

        if (registros.moveToNext()){
            mascotaAtual = new Mascota();
            mascotaAtual.setId(registros.getInt(0));
            mascotaAtual.setNombre(registros.getString(1));
            mascotaAtual.setRaiting(registros.getInt(2));
            mascotaAtual.setFoto(registros.getInt(3));
        }
        db.close();

        return mascotaAtual;
    }

    public List<Mascota> obtenerTodasLasFavoritas(){
        List<Mascota> mascotas = new ArrayList<Mascota>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_FAVORITOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros =  db.rawQuery(query,null);

        while (registros.moveToNext()){
            Mascota mascotaAtual = obtenerMascota(registros.getInt(1));
            mascotas.add(mascotaAtual);
        }
        db.close();
        return mascotas;
    }

    public void deleteAndCreateFavoritos(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(new StringBuilder().append("DROP TABLE ").append(ConstantesBaseDatos.TABLE_FAVORITOS).toString());
        db.execSQL(queryCrearTablaFavoritos);
        db.close();
    }

}
