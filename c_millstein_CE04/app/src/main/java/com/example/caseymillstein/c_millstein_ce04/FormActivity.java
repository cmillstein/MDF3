package com.example.caseymillstein.c_millstein_ce04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by caseymillstein on 2/8/16.
 */
public class FormActivity extends AppCompatActivity {

    FormFragment formFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formscreen);

        formFragment = FormFragment.newInstance();

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.formContainer, formFragment, FormFragment.TAG).commit();
        }else{
            formFragment = (FormFragment) getFragmentManager().findFragmentByTag(FormFragment.TAG);
        }







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_form, menu);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_camera:
                formFragment.camera();

        }



        return super.onOptionsItemSelected(item);
    }




}
