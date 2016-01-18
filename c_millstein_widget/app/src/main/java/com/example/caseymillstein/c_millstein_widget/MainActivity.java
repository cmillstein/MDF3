package com.example.caseymillstein.c_millstein_widget;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCar.onButtonClickListener {

    ArrayList<CarInformation> carInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void saveCarData(String _carName, String _carYear, String _carModel) {
        CarInformation temp = new CarInformation(_carName, _carYear, _carModel);



        try{
            FileInputStream fis = openFileInput("File.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            carInfo = (ArrayList<CarInformation>)ois.readObject();
            ois.close();
        }catch(Exception e){

        }

        carInfo.add(temp);


        try{
            FileOutputStream fos = openFileOutput("File.txt", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(carInfo);
            oos.close();
        }catch(Exception e){

        }

        Toast.makeText(this, "Car Added!", Toast.LENGTH_SHORT).show();
    }
}
