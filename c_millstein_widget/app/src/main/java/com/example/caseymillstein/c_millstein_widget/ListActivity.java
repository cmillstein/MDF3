package com.example.caseymillstein.c_millstein_widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caseymillstein on 1/17/16.
 */
public class ListActivity extends AppCompatActivity  implements  ListOfCars.onClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listscreen);


        ArrayList<CarInformation> carArray = new ArrayList<>();
        try{
            FileInputStream fis = openFileInput("File.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            carArray = (ArrayList<CarInformation>)ois.readObject();
            ois.close();
        }catch(Exception e){

        }
        ListOfCars tempList = ListOfCars.newInstance(carArray);
        getFragmentManager().beginTransaction().replace(R.id.listContainer5, tempList,ListOfCars.TAG).commit();



    }


    @Override
    public void listClick(CarInformation carList) {
        Intent viewCars = new Intent(this, ViewCarsActivity.class);
        viewCars.putExtra("mykey", carList);
        startActivityForResult(viewCars, 1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK && requestCode == 1){
            ListOfCars tempList = (ListOfCars)getFragmentManager().findFragmentByTag(ListOfCars.TAG);
            CarInformation carData = (CarInformation) data.getSerializableExtra("mykey");
            ArrayList<CarInformation> carAray = new ArrayList<>();
            try{
                FileInputStream fis = openFileInput("File.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                carAray = (ArrayList<CarInformation>)ois.readObject();
                ois.close();
            }catch(Exception e){

            }
            for(CarInformation moreCars:carAray){
                if(moreCars.getCarName().equals(carData.getCarName())){
                    carAray.remove(moreCars);
                    break;
                }

            }

            try{
                FileOutputStream fos = openFileOutput("File.txt", Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(carAray);
                oos.close();
            }catch(Exception e){

            }

            

            tempList.updateDisplayList(carAray);


        }


    }





}
