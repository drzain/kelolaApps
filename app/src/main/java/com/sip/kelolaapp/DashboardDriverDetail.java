package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class DashboardDriverDetail extends Activity
{
    private SessionManager session;
    private CardView btn_tranport;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_driver_detail);
        session = new SessionManager(getApplicationContext());
        action();
    }

    private void action()
    {
        btn_tranport=(CardView) findViewById(R.id.driver_btn_transport);
        btn_tranport.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardDriverDetail.this, DashboardDriverDestination.class);
                startActivity(i);
                finish();

            }
        });

    }

}
