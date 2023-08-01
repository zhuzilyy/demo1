package com.deer.myapplication4;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         init();
//         initName();
//         initName();
    }
    private void init(){
       Log.i("tag","lallal");
    }
    private void init21(){
        Log.i("tag","lallal");
    }
}