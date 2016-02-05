package com.example.caseymillstein.c_millstein_ce01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by caseymillstein on 2/1/16.
 *
 */

//Casey Millstein
//MDF3 - 201602
//CE01


public class ListActivity extends AppCompatActivity {

    public static final String TAG = "ListActivity.TAG";



    public static final String ACTION_SAVE_DATA = "com.fullsail.android.ACTION_SAVE_DATA";
    public static final String ACTION_DELETE_DATA = "com.fullsail.android.ACTION_DELETE_DATA";
    public static final String ACTION_UPDATE_LIST = "com.fullsail.android.ACTION_UPDATE_LIST";
    public static final String ACTION_VIEW_DATA = "com.fullsail.android.ACTION_VIEW_DATA";

    public static final String EXTRA_FIRST_NAME = "com.fullsail.android.EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "com.fullsail.android.EXTRA_LAST_NAME";
    public static final String EXTRA_AGE = "com.fullsail.android.EXTRA_AGE";


    MyBroadcastReceiver mReceiver;



    ListScreen listScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listscreen);


        listScreen = ListScreen.newInstance();

        if(savedInstanceState == null){
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.listContainer2, listScreen, ListScreen.TAG)
                    .commit();
        }else{

            listScreen = (ListScreen) getFragmentManager()
                    .findFragmentByTag(ListScreen.TAG);
        }







    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_add_to_list:
                System.out.println("Clicked");
                Intent intent = new Intent(this, AddScreenActivity.class);
                startActivity(intent);
                break;


        }


        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mReceiver = new MyBroadcastReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_SAVE_DATA);

        registerReceiver(mReceiver, filter);
    }


    @Override
    protected void onPause() {
        super.onPause();


        //unregisterReceiver(mReceiver);


    }



    public class MyBroadcastReceiver extends BroadcastReceiver{



      @Override
      public void onReceive(Context context, Intent intent) {





          if(intent.getAction().equals(ACTION_SAVE_DATA)){
              System.out.println("Recieved");
              String firstName;
              firstName = intent.getStringExtra(EXTRA_FIRST_NAME);
              String lastName;
              lastName = intent.getStringExtra(EXTRA_LAST_NAME);
              String age;
              age = intent.getStringExtra(EXTRA_AGE);
              System.out.println(firstName + lastName + age);


          }


      }


  }


}
