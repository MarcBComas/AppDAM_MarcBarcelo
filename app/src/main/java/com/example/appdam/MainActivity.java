package com.example.appdam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity{
    TextView tvscore1;
    TextView tvscore2;
    int score1;
    int score2;
    Toast limit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvscore1 = (TextView) findViewById(R.id.score1);
        tvscore2 = (TextView) findViewById(R.id.score2);
        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt("STATE_SCORE_1");
            score2 = savedInstanceState.getInt("STATE_SCORE_2");
            tvscore1.setText(String.valueOf(score1));
            tvscore2.setText(String.valueOf(score2));
        }
        limit = Toast.makeText(this, "Limite alcanzado", Toast.LENGTH_SHORT);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt("STATE_SCORE1", score1);
        outState.putInt("STATE_SCORE2", score2);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if(nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
            }
        }
        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus1:
                if (tvscore1.getText().toString().equals("100")) {
                    limit.show();
                } else {
                    tvscore1.setText(String.valueOf(Integer.parseInt(tvscore1.getText().toString()) + 1));
                }
                break;
            case R.id.plus2:
                if (tvscore2.getText().toString().equals("100")) {
                    limit.show();
                } else {
                    tvscore2.setText(String.valueOf(Integer.parseInt(tvscore2.getText().toString()) + 1));
                }
                break;
            case R.id.minus1:
                if (tvscore1.getText().toString().equals("0")) {
                    limit.show();
                } else {
                    tvscore1.setText(String.valueOf(Integer.parseInt(tvscore1.getText().toString()) - 1));
                }
                break;
            case R.id.minus2:
                if (tvscore2.getText().toString().equals("0")) {
                    limit.show();
                } else {
                    tvscore2.setText(String.valueOf(Integer.parseInt(tvscore2.getText().toString()) - 1));
                }
                break;
        }
    }

}
