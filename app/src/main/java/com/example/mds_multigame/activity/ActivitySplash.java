package com.example.mds_multigame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.example.mds_multigame.dao.AppDatabase;
import com.example.mds_multigame.utils.ActivityUtils;
import com.example.mds_multigame.R;

public class ActivitySplash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getDatabase(ActivitySplash.this);
                ActivityUtils.launchActivityWithSlide(ActivitySplash.this, CreatePlayerActivity.class);
            }
        }, 2000);
    }
}