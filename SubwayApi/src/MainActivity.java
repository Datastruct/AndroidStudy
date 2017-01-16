package com.example.owner.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.owner.myapplication.utils.Logger;
import com.example.owner.myapplication.view.activities.SearchActivity;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SearchActivity.class));
        finish();
    }
}