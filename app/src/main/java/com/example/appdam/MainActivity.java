package com.example.appdam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spn;
    Cursor cmntspn;
    ArrayList<Comentario> lista;
    Comentario cmnt = new Comentario();
    EditText titulo, texto;
    TextView titulo2, texto2;
    BaseDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new BaseDatos(this);
        titulo = (EditText) findViewById(R.id.nuevotitulo);
        texto = (EditText) findViewById(R.id.nuevotexto);
        titulo2 = (TextView) findViewById(R.id.tituloc);
        texto2 = (TextView) findViewById(R.id.textoc);
        spn = (Spinner) findViewById(R.id.comentariospinner);
        actualizarSpinner();
    }

    public void actualizarSpinner() {
        lista = db.getComentarios(spn, this);
        List<String> labels = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            labels.add(lista.get(i).getTitulo());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(dataAdapter);
        spn.setOnItemSelectedListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.crear:
                cmnt.setTitulo(titulo.getText().toString());
                cmnt.setTexto(texto.getText().toString());
                db.insertComentario(cmnt);
                actualizarSpinner();
                titulo.setText("");
                texto.setText("");
                break;
            case R.id.eliminar:
                db.deleteComentario(cmnt.getId());
                titulo2.setText("");
                texto2.setText("");
                actualizarSpinner();
                break;
            case R.id.seleccionarc:
                cmnt = lista.get(spn.getSelectedItemPosition());
                titulo2.setText(cmnt.getTitulo());
                texto2.setText(cmnt.getTexto());
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)
    {
        String item = parent.getItemAtPosition(pos).toString();
        cmnt = lista.get(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {

    }
}
