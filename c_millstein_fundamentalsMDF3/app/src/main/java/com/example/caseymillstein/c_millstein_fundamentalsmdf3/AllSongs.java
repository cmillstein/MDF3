package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

/**
 * Created by caseymillstein on 1/5/16.
 */
public class AllSongs {

    private String title;
    private int songResource;

    public AllSongs(String title, int songResource){
        this.title = title;
        this.songResource = songResource;

    }

    public String getTitle(){
        return title;
    }

    public int getSongResource(){
        return songResource;
    }

    public String getUriString(String packageName){
        return "android.resource://" + packageName + "/" + songResource;
    }


}
