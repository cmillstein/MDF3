package com.example.caseymillstein.c_millstein_ce05;

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
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by caseymillstein on 2/10/16.
 */
public class MusicService extends Service{



    int i = 0;
    MediaPlayer mp;
    int whenPause;
    ArrayList<Songs> playSongs = new ArrayList<>();

    public static final int STANDARD_NOTIFICATION = 0x01001;
    public static final int EXPANDED_NOTIFCATION = 0x1002;
    private static final int REQUEST_NOTIFY_LAUNCH = 0x02001;
    public static final int FOREGROUND_ID = 1026;



    private final IBinder mBinder = new BoundServiceBinder();
    Boolean paused = false;
    Boolean loop = false;
    Boolean shuffle = false;
    SeekBar seekBar;












    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



    public class BoundServiceBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    NotificationManager manager;
    NotificationCompat.Builder build;





    private void myNotification(){


        //Notification noti = build.build();

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



        manager.notify(STANDARD_NOTIFICATION, build.build());

        //startForeground(FOREGROUND_ID, noti);







    }

    //MAKING THE NOTIFACTION DISSAPPEAR WHEN USER CLICKS STOP BUTTON
    private void dissappearNoti(){
        manager.cancel(STANDARD_NOTIFICATION);
    }


    public void isSongLooping(boolean looper){
        loop = looper;
        mp.setLooping(looper);
    }



    public void isShuffleChecked(boolean shuffler){
        shuffle = shuffler;
    }


    @Override
    public void onCreate(){
        super.onCreate();




        //ARRAY OF SONGS AND ADDING THEM TO MY ARRAY LIST

        //Songs quick = new Songs("Quick", "android.resource://" + getPackageName() +"/raw/quick", "Quick", R.drawable.logc2);
        Songs neverBeen = new Songs("Never Been", "android.resource://" + getPackageName() + "/raw/never_been", "The Incredible True Story", R.drawable.logc2);
        Songs buriedAlive = new Songs("Buried Alive", "android.resource://" + getPackageName() + "/raw/buried_alive", "Under Pressure (Deluxe Edition)", R.drawable.logic);
        Songs imGone = new Songs("I'm Gone", "android.resource://" + getPackageName() + "/raw/im_gone", "5AM", R.drawable.five_am);

        //playSongs.add(quick);
        playSongs.add(neverBeen);
        playSongs.add(buriedAlive);
        playSongs.add(imGone);






    }



    Runnable runSeek = new Runnable() {
        @Override
        public void run() {
            int currentPosition = 0;
            int total = mp.getDuration();
            while (mp != null && currentPosition < total){
                try{
                    Thread.sleep(1000);
                    currentPosition = mp.getCurrentPosition();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MainFragment.seekBarUpdate(mp.getDuration(), mp.getCurrentPosition());
                System.out.println("Doesn't work");
            }
        }
    };


    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        if(fromUser){
            mp.seekTo(progress);
        }
    }










    public void onPlayClicked() {



        if (mp != null && mp.isPlaying()) {




            return;

        }

        if (mp != null && paused) {


            paused = false;
            //new Thread(runSeek).start();
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
                new Thread(runSeek).start();


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


    //PAUSE BUTTON LOGIC
    public void onPauseClicked(){
        if(mp == null || !mp.isPlaying()){
            return;
        }

        mp.pause();
        paused = true;
        whenPause = mp.getCurrentPosition();


    }

    //STOP BUTTON LOGIC
    public void onStopClicked(){

        if(mp.isPlaying() || paused) {
            //mp.release();
            mp.stop();


            dissappearNoti();
            paused = false;
        }else if  (mp == null || !mp.isPlaying()){
            return;
        }

    }


    //PREVIOUS SONG LOGIC
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


    //NEXT SONG LOGIC
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
