package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class DashboardDriver extends Activity
{
    private LinearLayout btn_logout;
    private SessionManager session;
    private CardView btn_load;
    private CardView btn_unload;
    private Button btn_take1;
    private CardView btn_take2;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_driver_new);
        session = new SessionManager(getApplicationContext());
      action();
    }

    private void action()
    {
        btn_take1= (Button) findViewById(R.id.btn_take1);
        btn_take1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardDriver.this, DashboardDriverDetail.class);
                startActivity(i);

            }
        });


        /*
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
                Intent i = new Intent(DashboardDriver.this, DriverUnload.class);
                startActivity(i);
            }
        });
        */
    }
}
