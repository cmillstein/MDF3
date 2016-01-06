package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mp;
    int whenPause;

    public static final int STANDARD_NOTIFICATION = 0x01001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }




    //Starting service
    public void startService(View view){

        Intent intent = new Intent(this, MusicService.class);
        startService(intent);


        if(mp==null) {

            mp = MediaPlayer.create(this, R.raw.im_gone);
            mp.start();
        }else if(!mp.isPlaying()){
            mp.seekTo(whenPause);
            mp.start();
        }


    }


    //Stopping service
    public void pauseService(View view){

        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);

        mp.pause();
        whenPause = mp.getCurrentPosition();


    }


    public void stopService(View view) {

        mp.release();
        mp = null;


    }
}
