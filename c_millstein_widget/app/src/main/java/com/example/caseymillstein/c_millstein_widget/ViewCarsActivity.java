package com.example.caseymillstein.c_millstein_widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by caseymillstein on 1/17/16.
 */
public class ViewCarsActivity extends AppCompatActivity implements ViewCars.onDeleteButtonClick{


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_container);

        Intent intent = getIntent();
        CarInformation moreInfo = (CarInformation)intent.getSerializableExtra("mykey");
        ViewCars tempList = ViewCars.newInstance(moreInfo);
        getFragmentManager().beginTransaction().replace(R.id.viewContainer, tempList, ViewCars.TAG).commit();



    }


    @Override
    public void removeCar(CarInformation carData) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("mykey", carData);
        setResult(RESULT_OK, returnIntent);
        finish();
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK && requestCode == 1){


        }
    }
}
