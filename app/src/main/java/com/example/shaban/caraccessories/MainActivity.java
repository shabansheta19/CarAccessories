package com.example.shaban.caraccessories;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.example.shaban.caraccessories.fragments.LoginFragment;
import com.example.shaban.caraccessories.utils.Utils;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    //private Button arabicBtn;
    //private Button englishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        //if (isOnline()) {
            // If saved instance state is null then replace login fragment
            if (savedInstanceState == null) {
                //fragmentManager.beginTransaction().replace(R.id.frameContainer, new LoginFragment(),
                  //      Utils.Login_Fragment).commit();
                startActivity(new Intent(this,CategoryActivity.class));
            }
        //} else {
            //startActivity(new Intent(MainActivity.this,NoConnectionActivity.class));
        //}

        /*arabicBtn = (Button)findViewById(R.id.arabic_activity);
        englishBtn = (Button)findViewById(R.id.english_activity);
        arabicBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage("ar");
            }
        });

        englishBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage("en");
            }
        });*/

    }

    //
    private void changeLanguage(String lang) {
        Locale locale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
    }

    //
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                .replace(R.id.frameContainer, new LoginFragment(), Utils.Login_Fragment).commit();
    }

    @Override
    public void onBackPressed() {
        // Find the tag of sign up and forgot password fragment
        Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Utils.SignUp_Fragment);
        Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Utils.ForgotPassword_Fragment);
        // Check if both are null or not
        // If both are not null then replace login fragment else do back pressed
        // task
        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }
}

