package com.example.cbmm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

public class AuthenticationActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    public static boolean onResetPassFragment = false;
    public static boolean onRegisterFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        frameLayout = findViewById(R.id.authentication_frameLayout);
        setDefaultFragment(new LoginFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if (onResetPassFragment || onRegisterFragment){
                onResetPassFragment = false;
                onRegisterFragment = false;
                setFragment(new LoginFragment());
                return false;
            }


        }


        return super.onKeyDown(keyCode, event);


    }

    private void setDefaultFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();


    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();


    }

}