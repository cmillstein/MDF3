package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.media.MediaExtractor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.caseymillstein.c_millstein_fundamentalsmdf3.MusicService.BoundServiceBinder;

import org.w3c.dom.Text;

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
    Button loopButton;
    static TextView album;
    TextView beginSong;
    TextView endSong;
    SeekBar seekBar;
    static TextView songTitle;
    static ImageView albumArt;
    private onButtonClickListener listener;



    Button play2;
    Button pause2;
    Button skipBackward2;
    Button skipForward2;
    Button stop2;
    TextView beginSong2;
    TextView endSong2;


    public interface onButtonClickListener{
        void play();
        void pause();
        void stop();
        void backSong();
        void nextSong();
        void loop();
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
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        endSong = (TextView) view.findViewById(R.id.endSong);
        beginSong = (TextView) view.findViewById(R.id.beginSong);
        //albumArt = (ImageView) view.findViewById(R.id.imageView);




        play2 = (Button) view.findViewById(R.id.play2);
        pause2 = (Button) view.findViewById(R.id.pause2);
        skipBackward2 = (Button) view.findViewById(R.id.skipBackward2);
        skipForward2 = (Button) view.findViewById(R.id.skipBackward2);
        stop2 = (Button) view.findViewById(R.id.stop2);
        beginSong2 = (TextView) view.findViewById(R.id.begin2);
        endSong2 = (TextView) view.findViewById(R.id.end2);







        //LOOP BUTTON
        loopButton = (Button) view.findViewById(R.id.loopButton);

        loopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                listener.loop();

            }

        });



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

        skipBackward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.backSong();
            }


        });

        skipForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.nextSong();
            }


        });






        return view;
    }

    public static void songName(String string){

        songTitle.setText(string);
    }

    public static void albumName(String string){
        album.setText(string);
    }


}
