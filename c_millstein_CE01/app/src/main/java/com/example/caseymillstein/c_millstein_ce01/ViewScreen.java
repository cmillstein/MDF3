package com.example.caseymillstein.c_millstein_ce01;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by caseymillstein on 2/1/16.
 */

//Casey Millstein
//MDF3 - 201602
//CE01

public class ViewScreen extends Fragment {

    public static final String TAG = "ViewScreen.TAG";


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState){
        View view = _inflater.inflate(R.layout.viewscreen, _container, false);


        return  view;
    }



}
