package com.example.caseymillstein.c_millstein_ce04;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {

    GoogleMap mMap;
    private static final int ERROR_DIALOG_REQUEST = 9001;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(servicesOK()){
            setContentView(R.layout.mapscreen);

                if(initMap()){
                    Toast.makeText(this, "Ready to map!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Map not connected", Toast.LENGTH_SHORT).show();
                }

        }else{

            setContentView(R.layout.activity_main);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_add:
                Intent intent = new Intent(this, FormActivity.class);
                startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }






    public boolean servicesOK(){


        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS){
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Cannot connect to mapping service", Toast.LENGTH_SHORT).show();
        }



        return false;
    }

    private boolean initMap(){
        if(mMap == null){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
        }

        return(mMap != null);

    }




}
