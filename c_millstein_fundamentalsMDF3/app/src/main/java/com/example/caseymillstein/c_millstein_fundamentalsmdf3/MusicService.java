package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
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

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by caseymillstein on 1/4/16.
 */
public class MusicService extends Service {

    int i = 0;
    MediaPlayer mp;
    int whenPause;

    ArrayList<AllSongs> playSongs = new ArrayList<>();



    public static final int STANDARD_NOTIFICATION = 0x01001;
    public static final int EXPANDED_NOTIFCATION = 0x1002;
    private static final int REQUEST_NOTIFY_LAUNCH = 0x02001;




    //BINDING SERVICE
    private final IBinder mBinder = new BoundServiceBinder();
    Boolean paused = false;


    public class BoundServiceBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }






    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    NotificationManager manager;
    NotificationCompat.Builder build;

    private void myNotification(){


        manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        build = new NotificationCompat.Builder(getBaseContext());
        Intent intent = new Intent(MusicService.this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MusicService.this, REQUEST_NOTIFY_LAUNCH, intent, 0);
        build.setAutoCancel(false);
        build.setOngoing(true);
        build.setContentIntent(pIntent);
        build.setSmallIcon(R.drawable.note);
        build.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.note));
        build.setContentTitle("Under Pressure");
        build.setContentText(playSongs.get(i).getmSong());
        manager.notify(EXPANDED_NOTIFCATION, build.build());






    }


    private void dissappearNoti(){
        manager.cancel(EXPANDED_NOTIFCATION);
    }




    @Override
    public void onCreate(){
        super.onCreate();



        AllSongs bounce = new AllSongs("Bounce", "android.resource://" + getPackageName() + "/raw/bounce");
        AllSongs buriedAlive = new AllSongs("Buried Alive", "android.resource://" + getPackageName() + "/raw/buried_alive");
        AllSongs imGone = new AllSongs("I'm Gone", "android.resource://" + getPackageName() + "/raw/im_gone");

        playSongs.add(bounce);
        playSongs.add(buriedAlive);
        playSongs.add(imGone);


    }







    public void onPlayClicked() {

            if (mp != null && mp.isPlaying()) {

                return;

            }

            if (mp != null && paused) {

                paused = false;
                mp.start();
                mp.seekTo(whenPause);
                return;

            }

            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

            try {

                mp.setDataSource(this, Uri.parse(String.valueOf(playSongs.get(i).getmTitle())));

            } catch (IOException e) {

                e.printStackTrace();
            }

            mp.prepareAsync();

            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    myNotification();

                }

            });

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    mp.stop();
                    mp.reset();
                    if (i == playSongs.size() - 1) {

                        i = 0;

                    } else {

                        i = i + 1;

                    }
                    try {

                        mp.setDataSource(getBaseContext(), Uri.parse(String.valueOf(playSongs.get(i).getmTitle())));

                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                    try {

                        mp.prepare();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                    MainFrag.songName(playSongs.get(i).getmSong());

                }

            });

            MainFrag.songName(playSongs.get(i).getmSong());

        }



    public void onPauseClicked(){
        if(mp == null || !mp.isPlaying()){
            return;
        }

        mp.pause();
        paused = true;
        whenPause = mp.getCurrentPosition();


    }

    public void onStopClicked(){

        if(mp.isPlaying() || paused) {
            mp.stop();
            dissappearNoti();
            paused = false;
        }else if  (mp == null || !mp.isPlaying()){
            return;
        }

    }


    public void onPreviousClicked(){
        if(mp != null && mp.isPlaying()){
            if(i==0){
                i=playSongs.size()-1;
            }else {
                i=i-1;
            }
            mp.stop();
            paused = false;
            onPlayClicked();
        }
    }


    public void onNextClicked(){

        if(mp != null && mp.isPlaying()){
            if(i==playSongs.size()-1){
                i=0;
            }else{
                i ++;
            }

            mp.stop();
            paused = false;
            onPlayClicked();

        }



    }

    @Override
    public void onDestroy(){

        //Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();

        dissappearNoti();

        mp.release();



        //super.onDestroy();
    }




}
