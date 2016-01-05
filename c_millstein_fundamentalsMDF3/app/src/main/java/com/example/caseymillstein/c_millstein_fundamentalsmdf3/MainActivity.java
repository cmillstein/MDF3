package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button playButton;
    Button pauseButton;
    Button rewind;
    Button fastForward;
    Button skipBackward;
    Button skipForward;
    TextView album;
    TextView song;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.playButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        rewind = (Button) findViewById(R.id.rewind);
        fastForward = (Button) findViewById(R.id.fastForward);
        skipBackward = (Button) findViewById(R.id.skipBack);
        skipForward = (Button) findViewById(R.id.skipForward);
        album = (TextView) findViewById(R.id.albumName);
        song = (TextView) findViewById(R.id.songName);






    }


    //Starting service
    public void startService(View view){

        Intent intent = new Intent(this, MusicService.class);
        startService(intent);


    }


    //Stopping service
    public void stopService(View view){

        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);



    }




}
