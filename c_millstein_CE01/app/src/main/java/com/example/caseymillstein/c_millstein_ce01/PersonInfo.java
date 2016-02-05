package com.example.caseymillstein.c_millstein_ce01;

import java.io.Serializable;

/**
 * Created by caseymillstein on 2/1/16.
 */
//Casey Millstein
//MDF3 - 201602
//CE01

public class PersonInfo implements Serializable {


    public static final String TAG = "PersonInfo.TAG";

    private static long serialVersionUID = 1;

    private String mFirstName;
    private String mLastName;
    private String mAge;

    public PersonInfo() {
        mFirstName = mLastName = mAge = "";
    }

    public PersonInfo(String _firstName, String _lastName, String _age){
        mFirstName = _firstName;
        mLastName = _lastName;
        mAge = _age;
    }

    public void setmFirstName(String mFirstName){
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName){
        this.mLastName = mLastName;
    }

    public void setmAge(String mAge){
        this.mAge = mAge;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName(){
        return mLastName;
    }

    public String getmAge(){
        return mAge;
    }



}
