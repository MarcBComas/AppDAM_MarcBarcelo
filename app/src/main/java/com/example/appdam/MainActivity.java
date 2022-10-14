package com.example.appdam;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    private TextView mainMessage;
    private GestureDetectorCompat myGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myGestureDetector = new GestureDetectorCompat(this,this);
        myGestureDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d("MainActivity","Tap");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("MainActivity","Double Tap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d("MainActivity","Tap");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("MainActivity","Screen Touched");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("MainActivity","Press");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("MainActivity","Tap Up");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("MainActivity","Scroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("MainActivity","Long Tap");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("MainActivity","Fling");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.myGestureDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }
}