package com.example.caseymillstein.c_millstein_fundamentalsmdf3new;

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
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by caseymillstein on 1/26/16.
 */
public class MusicService extends Service {


    MediaPlayer mp;
    int whenPause;
    int i = 0;
    ArrayList<AllSongs> playSongs = new ArrayList<>();

    public static final int STANDARD_NOTIFCATION = 0x01001;
    private static final int REQUEST_NOTIFY_LAUNCH = 0x02001;


    private final IBinder mBinder = new BoundServiceBinder();
    Boolean paused = false;
    Boolean loop = false;


    public class BoundServiceBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }




    @Nullable
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
        build.setAutoCancel(true);
        build.setOngoing(true);
        build.setContentIntent(pIntent);
        build.setSmallIcon(R.drawable.note);
        build.setLargeIcon(BitmapFactory.decodeResource(getResources(), playSongs.get(i).getmAlbumArt()));
        build.setContentTitle(playSongs.get(i).getmAlbum());
        build.setContentText(playSongs.get(i).getmSong());
        manager.notify(STANDARD_NOTIFCATION, build.build());

    }

    private void releaseNotification(){
        manager.cancel(STANDARD_NOTIFCATION);
    }


    public void isSongLooping(boolean looper){
        loop = looper;
        mp.setLooping(looper);
    }

    @Override
    public void onCreate(){
        super.onCreate();




        //ARRAY OF SONGS AND ADDING THEM TO MY ARRAY LIST

        //AllSongs quick = new AllSongs("Quick", "android.resource://" + getPackageName() +"/raw/quick", "Quick", R.drawable.logc2);
        AllSongs neverBeen = new AllSongs("Never Been", "android.resource://" + getPackageName() + "/raw/never_been", "The Incredible True Story", R.drawable.logc2);
        AllSongs buriedAlive = new AllSongs("Buried Alive", "android.resource://" + getPackageName() + "/raw/buried_alive", "Under Pressure (Deluxe Edition)", R.drawable.logic);
        AllSongs imGone = new AllSongs("I'm Gone", "android.resource://" + getPackageName() + "/raw/im_gone", "5AM", R.drawable.five_am);

        //playSongs.add(quick);
        playSongs.add(neverBeen);
        playSongs.add(buriedAlive);
        playSongs.add(imGone);




    }


    Runnable runSeek = new Runnable(){
        @Override
            public void run(){
            while(mp != null && mp.isPlaying()){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                MainFragment.seekBarUpdate(mp.getDuration(),mp.getCurrentPosition());
                System.out.println("Working");
            }
        }
    };

    //PLAY CLICKED
    public void onPlayClicked(){

        if (mp != null && mp.isPlaying()) {





            return;

        }

        if (mp != null && paused) {

            new Thread(runSeek).start();
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


                if (loop == false) {
                    if (i == playSongs.size() - 1) {

                        i = 0;

                    } else {

                        i = i + 1;

                    }
                } else {

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


                MainFragment.songName(playSongs.get(i).getmSong());
                MainFragment.albumName(playSongs.get(i).getmAlbum());
                MainFragment.albumArt(playSongs.get(i).getmAlbumArt());


            }


        });

        MainFragment.songName(playSongs.get(i).getmSong());
        MainFragment.albumName(playSongs.get(i).getmAlbum());
        MainFragment.albumArt(playSongs.get(i).getmAlbumArt());

    }


    //PAUSE CLICKED
    public void onPauseClicked(){

        if(mp == null || !mp.isPlaying()){
            return;
        }

        mp.pause();

        paused = true;
        whenPause = mp.getCurrentPosition();


    }

    //STOP BUTTON CLICKED
    public void onStopClicked(){

        if(mp.isPlaying() || paused) {
            //mp.release();
            mp.stop();

            releaseNotification();
            paused = false;
        }else if  (mp == null || !mp.isPlaying()){
            return;
        }

    }

    //PREVIOUS BUTTON CLICKED(){
    public void onPreviousClicked(){

        if (loop == false) {
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
        } else {

            mp.stop();
            paused = false;
            onPlayClicked();
        }


    }

    //NEXT BUTTON CLICKED
    public void onNextClicked(){


        if (loop == false) {
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
        } else {
            mp.stop();
            paused = false;
            onPlayClicked();


        }


    }


















}
