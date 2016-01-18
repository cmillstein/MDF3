package com.example.caseymillstein.c_millstein_widget;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by caseymillstein on 1/17/16.
 */
public class ViewCars extends Fragment {



    public static final String TAG = "ViewCars.TAG";

    private onDeleteButtonClick deleteCarInfo;
    TextView carNameText;
    TextView carYearText;
    TextView carModelText;
    Button deleteCar;

    public interface onDeleteButtonClick{
        public void removeCar(CarInformation carData);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof onDeleteButtonClick){
            deleteCarInfo = (onDeleteButtonClick)activity;
        }else{
            throw new IllegalArgumentException("This Doesn't Work X2");
        }

    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState){
        View view = _inflater.inflate(R.layout.viewscreen, _container, false);


        carNameText = (TextView) view.findViewById(R.id.carNameText);
        carYearText = (TextView) view.findViewById(R.id.carYearText);
        carModelText = (TextView) view.findViewById(R.id.carModelText);
        deleteCar = (Button) view.findViewById(R.id.deleteCar);

        deleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = getArguments();
                if (args != null && args.containsKey("mykey")) {
                    CarInformation moreCarInfo = (CarInformation) args.getSerializable("mykey");
                    deleteCarInfo.removeCar(moreCarInfo);
                }
            }
        });





        return view;
    }

    public static ViewCars newInstance(CarInformation returnCar){
        ViewCars storeList = new ViewCars();
        Bundle args = new Bundle();
        args.putSerializable("mykey", returnCar);
        storeList.setArguments(args);
        return storeList;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        View view = getView();

        Bundle args = getArguments();
        if(args != null && args.containsKey("mykey")){
            CarInformation carInfoAgain = (CarInformation)args.getSerializable("mykey");
            carNameText.setText(carInfoAgain.getCarName());
            carYearText.setText(carInfoAgain.getCarYear());
            carModelText.setText(carInfoAgain.getCarModel());
        }


    }







}
