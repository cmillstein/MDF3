package com.example.caseymillstein.c_millstein_fundamentalsmdf3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
