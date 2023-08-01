package com.deer.myapplication4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         init();
         name();
    }
    private void init(){
        Log.i("tag","1111111111");
        Log.i("tag","222222222");
        Log.i("tag","333333333");
        Log.i("tag","44444444444");
        Log.i("tag","55555555555");
    }
    private void name(){
        Log.i("tag","1111111111");
        Log.i("tag","222222222");
        Log.i("tag","333333333");
        Log.i("tag","44444444444");
        Log.i("tag","55555555555");
    }
}