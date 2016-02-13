package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by caseymillstein on 1/4/16.
 */
public class MusicService extends Service{

    int i = 0;
    MediaPlayer mp;
    int whenPause;
    SeekBar seekBar;
    private MainFrag mainFrag;

    ArrayList<AllSongs> playSongs = new ArrayList<>();



    public static final int STANDARD_NOTIFICATION = 0x01001;
    public static final int EXPANDED_NOTIFCATION = 0x1002;
    private static final int REQUEST_NOTIFY_LAUNCH = 0x02001;




    //BINDING SERVICE
    private final IBinder mBinder = new BoundServiceBinder();
    Boolean paused = false;
    Boolean loop = false;






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



    //SETTING UP NOTIFICATION AND PENDING INTENT
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
        manager.notify(STANDARD_NOTIFICATION, build.build());






    }

    //MAKING THE NOTIFACTION DISSAPPEAR WHEN USER CLICKS STOP BUTTON
    private void dissappearNoti(){
        manager.cancel(STANDARD_NOTIFICATION);
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



    Runnable runSeek = new Runnable() {
        @Override
        public void run() {
            while (mp != null && mp.isPlaying()){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MainFrag.seekBarUpdate(mp.getDuration(), mp.getCurrentPosition());
                System.out.println("Doesn't work");
            }
        }
    };






    //NOTE: WHEN ONE SONG ENDS, IT GOES TO THE NEXT SONG AUTOMATICALLY.  ALL SONGS WILL LOOP (3 SONGS IN TOTAL)
    public void onPlayClicked() {



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


                    MainFrag.songName(playSongs.get(i).getmSong());
                    MainFrag.albumName(playSongs.get(i).getmAlbum());
                    MainFrag.albumArt(playSongs.get(i).getmAlbumArt());


                }


            });

            MainFrag.songName(playSongs.get(i).getmSong());
            MainFrag.albumName(playSongs.get(i).getmAlbum());
            MainFrag.albumArt(playSongs.get(i).getmAlbumArt());

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

    @Override
    public void onDestroy(){

        //Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();

        //dissappearNoti();

        //mp.stop();



        //super.onDestroy();
    }




}
