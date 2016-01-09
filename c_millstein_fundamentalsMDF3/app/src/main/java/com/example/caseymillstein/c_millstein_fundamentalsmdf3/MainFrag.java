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
public class MainFrag extends Fragment{


    MediaPlayer mp;

    Button playButton;
    Button pauseButton;
    Button stop;
    //Button fastForward;
    Button skipBackward;
    Button skipForward;
    TextView album;
    static TextView songTitle;
    private onButtonClickListener listener;



    public interface onButtonClickListener{
        void play();
        void pause();
        void stop();
        void backSong();
        void nextSong();
    }


    public static final String TAG = "MainFrag.TAG";

    public static MainFrag newInstance(){
        return new MainFrag();
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _conatiner, Bundle _savedInstanceState){
        View view = _inflater.inflate(R.layout.main_frag, _conatiner, false);




        playButton = (Button) view.findViewById(R.id.playButton);
        pauseButton = (Button) view.findViewById(R.id.pauseButton);
        skipBackward = (Button) view.findViewById(R.id.skip_backward);
        skipForward = (Button) view.findViewById(R.id.skip_forward);
        stop = (Button) view.findViewById(R.id.stop);
        album = (TextView) view.findViewById(R.id.albumName);
        songTitle = (TextView) view.findViewById(R.id.songName);



        listener = (onButtonClickListener) getActivity();
        playButton.setOnClickListener(new View.OnClickListener(){


            @Override
                public void onClick(View v){
                listener.play();
            }


        });

        pauseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                listener.pause();
            }



        });

        stop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                listener.stop();
            }



        });

        skipBackward.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                listener.backSong();
            }



        });

        skipForward.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                listener.nextSong();
            }



        });












        return view;
    }

    public static void songName(String string){
        songTitle.setText(string);
    }


}
