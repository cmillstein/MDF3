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
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by caseymillstein on 1/4/16.
 */
public class MusicService extends Service {

    MediaPlayer mp;
    int whenPause;


    public static final int STANDARD_NOTIFICATION = 0x01001;
    public static final int EXPANDED_NOTIFCATION = 0x1002;
    private static final int FOREGROUND_NOTIFICATION = 0x01001;


    //ARRAY LIST
    public ArrayList<AllSongs> songs(){
        ArrayList<AllSongs> songArray = new ArrayList<>();

        songArray.add(new AllSongs("Bounce", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bounce)));
        songArray.add(new AllSongs("Buried Alive", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.buried_alive)));
        songArray.add(new AllSongs("I'm Gone", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.im_gone)));




        return songArray;
    }

    //BINDING SERVICE

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public class ServiceBinder extends Binder {

    }




    @Override
    public void onCreate(){
        super.onCreate();

        mp = MediaPlayer.create(this, R.raw.buried_alive);




    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

//        Toast.makeText(this, "Service Started..", Toast.LENGTH_LONG).show();
//        return START_STICKY;

        NotificationManager mgr = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.note);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.note));
        builder.setContentTitle("Under Pressure");
        builder.setContentText("Song Name");
        builder.setAutoCancel(false);
        builder.setOngoing(true);

        startForeground(FOREGROUND_NOTIFICATION, builder.build());

        Intent main = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, main, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pIntent);

        mp.start();


        return Service.START_NOT_STICKY;


        //return super.onStartCommand(intent, flags, startId);
    }



    public void onStart(Intent intent, int startId){

//        if(mp==null) {
//
//            mp.start();
//
//        }else if(!mp.isPlaying()){
//            mp.seekTo(whenPause);
//            mp.start();
//        }


    }


    public void onPause(){

        mp.pause();
        whenPause = mp.getCurrentPosition();


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




}
