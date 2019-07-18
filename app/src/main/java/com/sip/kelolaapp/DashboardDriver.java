package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardDriver extends Activity
{
    private LinearLayout btn_logout;
    private SessionManager session;
    private CardView btn_load, btn_unload;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_driver);
        session = new SessionManager(getApplicationContext());
        action();
    }

    private void action()
    {
        btn_load = (CardView) findViewById(R.id.transpoter_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardDriver.this, DriverReceiveOrder.class);
                startActivity(i);
            }
        });

        btn_unload = (CardView) findViewById(R.id.transpoter_unload);
        btn_unload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardDriver.this, DriverReceiveOrder.class);
                startActivity(i);
            }
        });

        btn_logout= (LinearLayout) findViewById(R.id.transporter_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            session.setLogin(false);
            Intent i = new Intent(DashboardDriver.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        });
    }
}
