package com.example.caseymillstein.c_millstein_ce04;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by caseymillstein on 2/8/16.
 */
public class MapFragment extends com.google.android.gms.maps.MapFragment {

    GoogleMap mMap;


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
            View view = _inflater.inflate(R.layout.mapscreen, _container, false);





        return view;



    }







}
