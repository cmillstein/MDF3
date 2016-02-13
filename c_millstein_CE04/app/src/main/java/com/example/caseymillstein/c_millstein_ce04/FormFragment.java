package com.example.caseymillstein.c_millstein_ce04;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by caseymillstein on 2/8/16.
 */
public class FormFragment extends Fragment {

    EditText locationName;
    EditText locationSubject;
    ImageView cameraImage;

    public static final String TAG = "FormFragment.TAG";

    public static final int REQUEST_PICTURE = 1;

    Uri mUri;


    public static FormFragment newInstance(){
        return new FormFragment();
    }



    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.formscreen, _container, false);


        locationName = (EditText) view.findViewById(R.id.locationName);
        locationSubject = (EditText) view.findViewById(R.id.locationSubject);
        cameraImage = (ImageView) view.findViewById(R.id.cameraImage);



        return view;



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;


        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_PICTURE){
            cameraImage.setImageBitmap(BitmapFactory.decodeFile(mUri.getPath(), options));
        }

        addPic(mUri);





    }








    public void camera(){
        mUri = getOutputUri();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, REQUEST_PICTURE);



    }

    private void addPic(Uri picUri){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(picUri);
        getActivity().sendBroadcast(intent);
    }

    private Uri getOutputUri(){
        String imageName = new SimpleDateFormat("MM-dd-yyyy_kk-mm-ss", Locale.US).format(new Date());
        File imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File appDirectory = new File(imageDirectory, "Mapping");
        appDirectory.mkdirs();
        File image = new File(appDirectory, imageName + ".jpg");
        try{
            image.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return Uri.fromFile(image);
    }



}
