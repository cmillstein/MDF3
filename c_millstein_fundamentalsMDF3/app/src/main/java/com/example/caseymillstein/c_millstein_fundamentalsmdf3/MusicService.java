package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by caseymillstein on 1/4/16.
 */
public class MusicService extends Service {

    MediaPlayer mp;
    int whenPause;


    public static final int STANDARD_NOTIFICATION = 0x01001;
    public static final int EXPANDED_NOTIFCATION = 0x1002;


    @Override
    public void onCreate(){
        super.onCreate();

        mp = MediaPlayer.create(this, R.raw.buried_alive);



    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

//        Toast.makeText(this, "Service Started..", Toast.LENGTH_LONG).show();
//        return START_STICKY;

//        NotificationManager mgr = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setSmallIcon(R.drawable.note);
//        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.note));
//        builder.setContentTitle("Under Pressure");
//        builder.setContentText("Song Name");
//        mgr.notify(STANDARD_NOTIFICATION, builder.build());
//
//        Intent main = new Intent(this, MainActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this, 0, main, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pIntent);

        if(mp==null) {

            mp.start();

        }else if(!mp.isPlaying()){
            mp.seekTo(whenPause);
            mp.start();
        }


        return 1;


        //return super.onStartCommand(intent, flags, startId);
    }

    public void onStart(Intent intent, int startId){


    }


    public void onPause(){




    }

    public void onStop(){



    }



    @Override
    public void onDestroy(){

        //Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();


        mp.stop();
        mp.release();



        //super.onDestroy();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
