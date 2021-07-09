package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //to make a stop Watch we have to use two variables
    private int seconds=0;
    private boolean running=false;
    private boolean wasRunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            //it means the activity is recreated
            //fetching values from the bundle
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }


    //Life Cycle methods:
    protected void onResume(){
        super.onResume();
        //activity is about to again get visible
        if(wasRunning)
            running=true;

    }

    protected void onPause(){
        super.onPause();
        //activity gets invisible
        wasRunning=running;
        running=false;
        //timer gets stopped
    }


    //Creating methods for the button Onclick
    public void onClickStartButton(View view){
        running=true;


    }

    public void onClickStopButton(View view){
        running=false;


    }

    public void onClickResetButton(View view) {
        running = false;
        seconds = 0;

    }

    private void runTimer(){
       final TextView timeView=(TextView)findViewById(R.id.timerdisplay);
       final Handler handler=new Handler();
       handler.post(new Runnable() {
           @Override
           public void run() {
               TextView timeView=(TextView)findViewById(R.id.timerdisplay);
               int hours = seconds/3600;
               int minutes = (seconds%3600)/60;
               int secs = seconds%60;
               String time = String.format("%d:%02d:%02d", hours, minutes, secs);
               timeView.setText(time);
               if(running){
                   seconds++;
               }
               handler.postDelayed(this,1000);
           }

       });
    }








}