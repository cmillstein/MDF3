package com.example.caseymillstein.c_millstein_ce02;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;

/**
 * Created by caseymillstein on 2/3/16.
 */
public class GridScreen extends Fragment {


    public static final String TAG = "GridScreen.TAG";

    GridLayout gridLayout;


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState){
        View view = _inflater.inflate(R.layout.grid_view, _container, false);


        gridLayout = (GridLayout) view.findViewById(R.id.gridLayout);



        return  view;
    }




}
