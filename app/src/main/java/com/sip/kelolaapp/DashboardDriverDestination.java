package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class DashboardDriverDestination extends Activity
{
    private SessionManager session;
    private CardView btn_tranport_des;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_driver_destination);
        session = new SessionManager(getApplicationContext());
        action();
    }

    private void action()
    {
        btn_tranport_des=(CardView) findViewById(R.id.driver_btn_des);
        btn_tranport_des.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardDriverDestination.this, DashboardActivityNav.class);
                startActivity(i);
                finish();

            }
        });

    }
}
