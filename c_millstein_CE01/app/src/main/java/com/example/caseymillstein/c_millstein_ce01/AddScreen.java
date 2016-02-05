package com.example.caseymillstein.c_millstein_ce01;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by caseymillstein on 2/1/16.
 */
//Casey Millstein
//MDF3 - 201602
//CE01

public class AddScreen extends Fragment{

    public static final String TAG = "AddScreen.TAG";

    EditText addFirstName;
    EditText addLastName;
    EditText addAge;


    public static AddScreen newInstance(){
        return new AddScreen();
    }


    public PersonInfo returnInfo(){
        String firstName;
        String lastName;
        String age;

        firstName = addFirstName.getText().toString();
        lastName = addLastName.getText().toString();
        age = addAge.getText().toString();
        int ageInt = Integer.parseInt(age);
        PersonInfo newPersonInfo = new PersonInfo(firstName, lastName, age);

        return newPersonInfo;
    }



    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState){
        View view = _inflater.inflate(R.layout.addscreen, _container, false);

        addFirstName = (EditText) view.findViewById(R.id.addFirstName);
        addLastName = (EditText) view.findViewById(R.id.addLastName);
        addAge = (EditText) view.findViewById(R.id.addAge);

//        addFirstName.setHint("First Name");
//        addLastName.setHint("Last Name");
//        addAge.setHint("Age");






        return  view;
    }




}
