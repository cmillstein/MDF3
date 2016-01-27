package com.example.caseymillstein.c_millstein_fundamentalsmdf3new;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by caseymillstein on 1/26/16.
 */
public class MainFragment extends Fragment {

    Button playButton;
    Button pauseButton;
    Button stop;
    Button skipBackward;
    Button skipForward;
    CheckBox loopButton;
    static TextView album;
    TextView beginSong;
    TextView endSong;
    static SeekBar seekBar;
    static TextView songTitle;
    static ImageView albumArt;
    private onButtonClickListener mListener;


    public interface onButtonClickListener{
        void play();
        void pause();
        void stop();
        void backSong();
        void nextSong();
        void loopSong(boolean looper);

    }

    public static final String TAG = "MainFrag.TAG";

    public static MainFragment newInstance(){
        return new MainFragment();
    }



    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _conatiner, Bundle _savedInstanceState){
        View view = _inflater.inflate(R.layout.main_fragment, _conatiner, false);




        playButton = (Button) view.findViewById(R.id.playButton);
        pauseButton = (Button) view.findViewById(R.id.pauseButton);
        skipBackward = (Button) view.findViewById(R.id.skip_backward);
        skipForward = (Button) view.findViewById(R.id.skip_forward);
        stop = (Button) view.findViewById(R.id.stop);
        album = (TextView) view.findViewById(R.id.albumName);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        endSong = (TextView) view.findViewById(R.id.endSong);
        beginSong = (TextView) view.findViewById(R.id.beginSong);
        albumArt = (ImageView) view.findViewById(R.id.imageView);



        albumArt.setImageResource(R.drawable.logc2);






        //LOOP BUTTON
        loopButton = (CheckBox) view.findViewById(R.id.loopBox);

        loopButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mListener.loopSong(true);
                } else {
                    mListener.loopSong(false);
                }
            }
        });




        songTitle = (TextView) view.findViewById(R.id.songName);





        mListener = (onButtonClickListener) getActivity();


        playButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v){
                mListener.play();
            }


        });

        pauseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                mListener.pause();
            }



        });

        stop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                mListener.stop();
            }



        });

        skipBackward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.backSong();
            }


        });

        skipForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.nextSong();
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

    public static void albumArt(int resID){
        //albumArt.setImageDrawable(Drawable.createFromPath(string));
        albumArt.setImageResource(resID);
    }

    public static void seekBarUpdate(int duration, int position){
        seekBar.setMax(duration);
        seekBar.setProgress(position);
    }






}
