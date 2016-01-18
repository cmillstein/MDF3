package com.example.caseymillstein.c_millstein_widget;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by caseymillstein on 1/17/16.
 */
public class ListOfCars extends ListFragment{


    private onClickListener buttonClickList;
    public static final String TAG = "ListOfCars.TAG";


    public interface onClickListener{
        public void listClick(CarInformation carList);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof onClickListener){
            buttonClickList = (onClickListener)activity;
        }else{
            throw new IllegalArgumentException("This Doesn't Work");
        }
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String carName = (String) l.getItemAtPosition(position);
        CarInformation newCarObject = new CarInformation();
        Bundle args = getArguments();
        if (args != null && args.containsKey("mykey")){
            ArrayList<CarInformation> carArray = (ArrayList<CarInformation>) args.getSerializable("mykey");
            for(CarInformation moreCars:carArray){
                if(moreCars.getCarName().equals(carName)){
                    newCarObject = moreCars;
                    break;
                }
            }
        }
        buttonClickList.listClick(newCarObject);
    }


    public void updateDisplayList(ArrayList<CarInformation> returnCarInfo){
        String[] stringArray = new String[returnCarInfo.size()];
        int loop = 0;
        for(CarInformation typeOfCar:returnCarInfo){
            stringArray[loop] = typeOfCar.getCarName();
            loop++;
        }
        ArrayAdapter<String> carAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stringArray);
        setListAdapter(carAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if(args != null && args.containsKey("mykey")){
            updateDisplayList((ArrayList<CarInformation>)args.getSerializable("mykey"));
        }
    }


    public static ListOfCars newInstance(ArrayList<CarInformation> returnCar){
        ListOfCars storeList = new ListOfCars();
        Bundle args = new Bundle();
        args.putSerializable("mykey", returnCar);
        storeList.setArguments(args);
        return storeList;
    }














}
