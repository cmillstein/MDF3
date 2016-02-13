package com.example.caseymillstein.c_millstein_ce05;

/**
 * Created by caseymillstein on 2/10/16.
 */
public class Songs {



    private String mSong;
    private String mTitle;
    private String mAlbum;
    private int mAlbumArt;





    public Songs(String _song, String _title, String _album, int _albumArt){
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
