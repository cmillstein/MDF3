package com.example.caseymillstein.c_millstein_ce05;

import android.bluetooth.BluetoothClass;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainFragment.onButtonClickListener, ServiceConnection {

    MainFragment mainFragment;

    MusicService myService;
    boolean bound = false;
    MusicService.BoundServiceBinder mBinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mainFragment = MainFragment.newInstance();

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.fragment_container_one, mainFragment, MainFragment.TAG).commit();
        }else{
            mainFragment = (MainFragment) getFragmentManager().findFragmentByTag(MainFragment.TAG);
        }






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
    protected void onStop() {
        super.onStop();

        if(bound){

            unbindService(this);

        }

        bound = false;

    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mBinder = (MusicService.BoundServiceBinder) service;
        myService = mBinder.getService();
        bound = true;


    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

        bound = false;
    }

    @Override
    public void play() {

        myService.onPlayClicked();



    }

    @Override
    public void pause() {

            myService.onPauseClicked();



    }

    @Override
    public void stop() {

            myService.onStopClicked();

    }

    @Override
    public void backSong() {
            myService.onPreviousClicked();

    }

    @Override
    public void nextSong() {
            myService.onNextClicked();


    }

    @Override
    public void loopSong(boolean looper) {
            myService.isSongLooping(looper);


    }

    @Override
    public void shuffleSong(boolean shuffler){
        myService.isShuffleChecked(shuffler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);

    }
}
