package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.net.Uri;

/**
 * Created by caseymillstein on 1/5/16.
 */
public class AllSongs {

    private String mTitle;
    private Uri mUri;


    public AllSongs(String title, Uri uri){
        mTitle = title;
        mUri = uri;

    }

    public String getmTitle(){
        return mTitle;
    }

    public Uri getmUri(){
        return mUri;
    }

}
