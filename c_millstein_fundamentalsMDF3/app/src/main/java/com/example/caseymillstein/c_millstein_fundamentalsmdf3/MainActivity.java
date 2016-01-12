package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity  implements MainFrag.onButtonClickListener, ServiceConnection{




    MusicService myService;
    boolean bound = false;
    MusicService.BoundServiceBinder mBinder;



    public static final int STANDARD_NOTIFICATION = 0x01001;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //ORIENTATION
        int currentOrientation = getResources().getConfiguration().orientation;






    }

    @Override
    protected void onStart(){
         super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
        bound = true;

    }





    @Override
    public void onServiceConnected(ComponentName name, IBinder service){
        mBinder = (MusicService.BoundServiceBinder) service;
        myService = mBinder.getService();
        bound = true;
        System.out.println("Bound");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        bound = false;
    }



    @Override
    protected void onStop() {
        super.onStop();

        if(bound){

            unbindService(this);

        }

        bound = false;

    }


    @Override
    public void play() {

        if(bound) {

            myService.onPlayClicked();

        }

    }

    @Override
    public void pause() {

        if(bound) {

            myService.onPauseClicked();

        }

    }

    @Override
    public void stop() {

        if(bound) {

            myService.onStopClicked();

        }

    }




    @Override
    public void backSong() {

        if(bound) {

            myService.onPreviousClicked();

        }

    }



    @Override
    public void nextSong() {

        if(bound) {

            myService.onNextClicked();

        }

    }


    @Override
    public void loop(){
        if(bound){

            myService.onLoopClicked();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);

    }



}
