package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
//import com.onesignal.OneSignal;

import io.fabric.sdk.android.Fabric;

public class DashboardActivityNav extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{

    private SessionManager session;
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_nav);
/*
        Fabric.with(this, new Crashlytics());
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();*/



        session = new SessionManager(getApplicationContext());
        // Check if user is already logged in or not
        if (!session.isLoggedIn())
        {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(DashboardActivityNav.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        loadFragment(new DashboardActivity());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.home_menu:
                fragment = new DashboardActivity();
                break;
            case R.id.history_menu:
                fragment = new FragmentHistory();
                break;
            case R.id.chat_menu:
                fragment = new FragmentChat();
                break;
            case R.id.account_menu:
                fragment = new FragmentAccount();
                break;
        }
        return loadFragment(fragment);
    }


}
