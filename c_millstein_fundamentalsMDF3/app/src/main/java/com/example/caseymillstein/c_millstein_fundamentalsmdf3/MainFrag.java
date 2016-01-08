package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaExtractor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.caseymillstein.c_millstein_fundamentalsmdf3.MusicService.BoundServiceBinder;

/**
 * Created by caseymillstein on 1/4/16.
 */
public class MainFrag extends Fragment implements ServiceConnection{


    MediaPlayer mp;

    Button playButton;
    Button pauseButton;
    Button stop;
    //Button fastForward;
    Button skipBackward;
    Button skipForward;
    TextView album;
    TextView song;


    MusicService mService;
    boolean mBound;

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _conatiner, Bundle _savedInstanceState){
        View view = _inflater.inflate(R.layout.main_frag, _conatiner, false);




        playButton = (Button) view.findViewById(R.id.playButton);
        pauseButton = (Button) view.findViewById(R.id.pauseButton);
        skipBackward = (Button) view.findViewById(R.id.skip_backward);
        skipForward = (Button) view.findViewById(R.id.skip_forward);
        stop = (Button) view.findViewById(R.id.stop);
        album = (TextView) view.findViewById(R.id.albumName);
        song = (TextView) view.findViewById(R.id.songName);




        return view;
    }








    @Override
    public void onServiceConnected(ComponentName name, IBinder service){
        MusicService.BoundServiceBinder binder = (MusicService.BoundServiceBinder)service;
        mService = binder.getService();
        mBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
        mBound = false;
    }




}
