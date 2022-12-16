package com.example.appdam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
    private EditText link;
    private Button download;
    private TextView urlcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        link = (EditText) findViewById(R.id.ETurl);
        download = (Button) findViewById(R.id.Bdownload);
        urlcontent = (TextView) findViewById(R.id.linkresult);
    }

    public void downloadurl(View v) throws IOException {
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = con.getActiveNetworkInfo();
        if(nInfo !=null && nInfo.isConnected()){
            new DownloadWebpageText().execute(link.getText().toString());
        }else {
            urlcontent.setText("No network connection available");
        }
    }

    private class DownloadWebpageText extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            try{
                return downloadUrl(url[0]);
            } catch (IOException e){
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            urlcontent.setText(result);
        }

    }
    private String downloadUrl(String myURL) throws IOException{
        InputStream is = null;
        try{
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            String contentAsString = reader(is);
            return contentAsString;


        }finally {
            if(is != null){
                is.close();
            }
        }
    }

    public String reader(InputStream stream) throws IOException {
        String textReturn = "";
        int ch = stream.read();
        while(ch!=-1){
            textReturn = textReturn + Character.toString((char)ch);
        }
        return textReturn;
    }

}
