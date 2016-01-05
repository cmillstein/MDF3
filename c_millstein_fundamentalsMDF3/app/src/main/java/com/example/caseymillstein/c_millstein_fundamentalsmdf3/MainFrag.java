package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by caseymillstein on 1/4/16.
 */
public class MainFrag extends Fragment {



    Button playButton;
    Button pauseButton;
    Button rewind;
    Button fastForward;
    Button skipBackward;
    Button skipForward;
    TextView album;
    TextView song;


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _conatiner, Bundle _savedInstanceState){
        View view = _inflater.inflate(R.layout.main_frag, _conatiner, false);

        playButton = (Button) view.findViewById(R.id.playButton);
        pauseButton = (Button) view.findViewById(R.id.pauseButton);
        rewind = (Button) view.findViewById(R.id.rewind);
        fastForward = (Button) view.findViewById(R.id.fastForward);
        skipBackward = (Button) view.findViewById(R.id.skipBack);
        skipForward = (Button) view.findViewById(R.id.skipForward);
        album = (TextView) view.findViewById(R.id.albumName);
        song = (TextView) view.findViewById(R.id.songName);


        return view;
    }



}
