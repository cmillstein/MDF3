package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.media.Image;
import android.net.Uri;

/**
 * Created by caseymillstein on 1/5/16.
 */
public class AllSongs {

    private String mSong;
    private String mTitle;
    private String mAlbum;
    private int mAlbumArt;





    public AllSongs(String _song, String _title, String _album, int _albumArt){
        mSong = _song;
        mTitle = _title;
        mAlbum = _album;
        mAlbumArt = _albumArt;



    }



    public String getmSong(){
        return mSong;
    }


    public String getmTitle(){
        return mTitle;
    }

    public String getmAlbum(){
        return mAlbum;
    }

    public int getmAlbumArt(){
        return mAlbumArt;
    }




}
