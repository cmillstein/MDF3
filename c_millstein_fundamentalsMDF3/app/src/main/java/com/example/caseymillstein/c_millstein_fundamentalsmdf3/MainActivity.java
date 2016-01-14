package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements MainFrag.onButtonClickListener, ServiceConnection{



    int i = 0;

    MusicService myService;
    boolean bound = false;
    MusicService.BoundServiceBinder mBinder;
    MediaPlayer mp = new MediaPlayer();




    Boolean loop = false;
    private SeekBar seekBar;
    Button play;
    Button stopButton;
    TextView endSong;
    TextView beginSong;



    public static final int STANDARD_NOTIFICATION = 0x01001;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBar = (SeekBar) findViewById(R.id.seekBar);
        play = (Button) findViewById(R.id.playButton);
        stopButton = (Button) findViewById(R.id.stop);
        beginSong = (TextView) findViewById(R.id.beginSong);
        endSong = (TextView) findViewById(R.id.endSong);




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
    public void loopSong(boolean looper) {

        if(bound) {

            myService.isSongLooping(looper);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);

    }


}
