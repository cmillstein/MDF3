package com.example.caseymillstein.c_millstein_ce01;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by caseymillstein on 2/1/16.
 */
//Casey Millstein
//MDF3 - 201602
//CE01

public class ListScreen extends ListFragment {


    public static final String TAG = "ListScreen.TAG";

    public static ListScreen newInstance(){
        return new ListScreen();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);



    }


    public void updateList(){




    }






}
