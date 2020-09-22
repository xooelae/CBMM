package com.example.cbmm;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SystemClock.sleep(3000);
        Intent intent = new Intent(SplashActivity.this,AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }
}
