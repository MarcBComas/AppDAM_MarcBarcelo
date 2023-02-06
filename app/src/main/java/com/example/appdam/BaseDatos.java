package com.example.appdam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {
    private static final String DBNAME = "myDB";
    private static final int DBVERSION = 1;
    private static final String TABLENAME = "comentarios";
    private ArrayList<Comentario> listaComentarios = new ArrayList<Comentario>();

    public BaseDatos(Context c) {
        super(c,DBNAME,null,DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLENAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, texto TEXT);");
        Comentario cmnt = new Comentario("Nota 1", "Ejemplo de nota");
        insertComentario(cmnt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }

    public ArrayList<Comentario> getComentarios(Spinner spn, Context cttx) {
        listaComentarios.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> labels = new ArrayList<String>();
        Cursor c = db.rawQuery("select * from "+TABLENAME, null);

        if (c.getCount() != 0) {
            c.moveToFirst();
            while (c.moveToNext()) {
                Comentario cmnt = new Comentario(c.getString(c.getColumnIndex("titulo")), c.getString(c.getColumnIndex("texto")));
                cmnt.setId(c.getInt(c.getColumnIndex("_id")));
                listaComentarios.add(cmnt);
            }

        }
        c.close();
        db.close();
        return listaComentarios;
    }

    public void insertComentario(Comentario cmnt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titulo", cmnt.getTitulo());
        cv.put("texto", cmnt.getTexto());
        db.insert(TABLENAME, null, cv);
        db.close();
    }

    public void deleteComentario(int id) {
        try {
            String[] args = { String.valueOf(id)};
            getWritableDatabase().delete(TABLENAME, "_id=?",args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
