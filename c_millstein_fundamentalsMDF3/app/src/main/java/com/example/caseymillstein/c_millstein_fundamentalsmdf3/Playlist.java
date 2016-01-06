package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import java.util.ArrayList;

/**
 * Created by caseymillstein on 1/5/16.
 */
public class Playlist {

    private ArrayList<AllSongs> songs = new ArrayList<>();
    private int current;



    public Playlist(){

        songs.add(new AllSongs("Bounce", R.raw.bounce));
        songs.add(new AllSongs("Buried Alive", R.raw.buried_alive));
        songs.add(new AllSongs("I'm Gone", R.raw.im_gone));

        current = 0;

    }




}
