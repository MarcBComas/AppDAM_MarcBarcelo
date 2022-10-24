package com.example.appdam;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class MainActivity extends AppCompatActivity{
    private Button countButton;
    private TextView countTextView;
    private int numCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTextView = (TextView) findViewById(R.id.count_text_view);
        countTextView.setText(numCount);
        countButton = (Button) findViewById(R.id.count_button);
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
            }
        });
    }
    private void count() {
        countTextView.setText(numCount++);
    }

}