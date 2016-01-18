package com.example.caseymillstein.c_millstein_widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.FormatFlagsConversionMismatchException;

/**
 * Created by caseymillstein on 1/17/16.
 */
public class AddCar extends Fragment {

    TextView carName;
    TextView carYear;
    TextView carModel;
    Button addCar;
    Button carList;

    private onButtonClickListener onButtonClicked;

    public interface  onButtonClickListener{
        public void saveCarData(String _carName, String _carYear, String _carModel);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof onButtonClickListener){
            onButtonClicked = (onButtonClickListener)activity;
        }else{
            throw new IllegalArgumentException("It's wrong");
        }
    }




    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.addcar, _container, false);


        carName = (TextView) view.findViewById(R.id.carName);
        carYear = (TextView) view.findViewById(R.id.carYear);
        carModel = (TextView) view.findViewById(R.id.carModel);
        addCar = (Button) view.findViewById(R.id.addCarButton);
        carList = (Button) view.findViewById(R.id.carListButton);



        carName.setHint("Car Name");
        carYear.setHint("Car Year");
        carModel.setHint("Car Model");


        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = carName.getText().toString();
                String year = carYear.getText().toString();
                String model = carModel.getText().toString();

                if (!name.isEmpty() && !year.isEmpty() && !model.isEmpty()) {
                    onButtonClicked.saveCarData(name, year, model);
                    carName.setText("");
                    carYear.setText("");
                    carModel.setText("");
                } else {
                    Toast.makeText(getActivity(), "Please Add A Car", Toast.LENGTH_SHORT).show();
                }
            }
        });

        carList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToList = new Intent(getActivity(), ListActivity.class);
                startActivity(goToList);
            }
        });


        return view;

    }
}
