package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by caseymillstein on 1/11/16.
 */

public class LandFrag extends Fragment {


    Button play2;
    Button pause2;
    Button skipBackward2;
    Button skipForward2;
    Button stop2;
    TextView beginSong2;
    TextView endSong2;
    CheckBox loopButton;
    static TextView songTitle2;
    static TextView album2;
    private onButtonClickListener listener;




    public static final String TAG = "LandFrag.TAG";

    public static LandFrag newInstance(){
        return new LandFrag();
    }

    public interface onButtonClickListener{
        void play2();
        void pause2();
        void stop2();
        void backSong2();
        void nextSong2();
        void loopSong2(boolean looper2);
    }


    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.landscape_frag, _container, false);


        play2 = (Button) view.findViewById(R.id.play2);
        pause2 = (Button) view.findViewById(R.id.pause2);
        skipBackward2 = (Button) view.findViewById(R.id.skipBackward2);
        skipForward2 = (Button) view.findViewById(R.id.skipForward2);
        stop2 = (Button) view.findViewById(R.id.stop2);
        beginSong2 = (TextView) view.findViewById(R.id.begin2);
        endSong2 = (TextView) view.findViewById(R.id.end2);
        loopButton = (CheckBox) view.findViewById(R.id.loopBox);
        songTitle2 = (TextView) view.findViewById(R.id.songTitle2);
        album2 = (TextView) view.findViewById(R.id.album2);







        return view;




    }


}




