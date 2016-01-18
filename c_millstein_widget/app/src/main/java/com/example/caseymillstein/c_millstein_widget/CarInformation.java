package com.example.caseymillstein.c_millstein_widget;

import java.io.Serializable;

/**
 * Created by caseymillstein on 1/17/16.
 */
public class CarInformation implements Serializable{



    private static long serialVersionUID = 50;

    private String carName;
    private String carYear;
    private String carModel;

    public CarInformation(){carName = carYear = carModel = "";}

    public CarInformation(String _carName, String _carYear, String _carModel){
        carName = _carName;
        carYear = _carYear;
        carModel = _carModel;

    }

    public void setCarName(String carName){
        this.carName = carName;
    }

    public void setCarYear(String carYear){
        this.carYear = carYear;
    }

    public void setCarModel(String carModel){
        this.carModel = carModel;
    }


    public String getCarName(){
        return carName;
    }

    public String getCarYear(){
        return carYear;
    }

    public String getCarModel(){
        return carModel;
    }


}
