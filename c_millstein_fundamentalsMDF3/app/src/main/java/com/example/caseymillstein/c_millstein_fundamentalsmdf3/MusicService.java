package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by caseymillstein on 1/4/16.
 */
public class MusicService extends Service {



    @Override
    public void onCreate(){
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        Toast.makeText(this, "Service Started..", Toast.LENGTH_LONG).show();
        return START_STICKY;

        //return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy(){

        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();



        //super.onDestroy();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
