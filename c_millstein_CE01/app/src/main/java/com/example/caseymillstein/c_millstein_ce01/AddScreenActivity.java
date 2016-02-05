package com.example.caseymillstein.c_millstein_ce01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;



/**
 * Created by caseymillstein on 2/1/16.
 */
//Casey Millstein
//MDF3 - 201602
//CE01

public class AddScreenActivity extends AppCompatActivity {

    public static final String TAG = "AddScreenActivity.TAG";

    AddScreen addScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addScreen = AddScreen.newInstance();


        if (savedInstanceState == null){
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_one, addScreen, AddScreen.TAG)
                    .commit();
        }else{
            addScreen = (AddScreen) getFragmentManager()
                    .findFragmentByTag(AddScreen.TAG);
        }






    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu); //Check mark button icon




        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_save:
                System.out.println("Saved");
                saveData(addScreen.returnInfo());





                break;


        }




        return super.onOptionsItemSelected(item);
    }


    public void saveData(PersonInfo newPersonInfo){
        Intent intent = new Intent(ListActivity.ACTION_SAVE_DATA);
        intent.putExtra(ListActivity.EXTRA_FIRST_NAME, newPersonInfo.getmFirstName());
        intent.putExtra(ListActivity.EXTRA_LAST_NAME, newPersonInfo.getmLastName());
        intent.putExtra(ListActivity.EXTRA_AGE, newPersonInfo.getmAge());
        intent.setAction(ListActivity.ACTION_SAVE_DATA);

        sendBroadcast(intent);





    }




}
