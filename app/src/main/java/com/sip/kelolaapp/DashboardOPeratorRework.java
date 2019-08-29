package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardOPeratorRework extends Activity
{
    private Button btn_rework;
    private SessionManager session;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_operator_rework);
        session = new SessionManager(getApplicationContext());

        btn_rework = (Button)findViewById(R.id.btn_rework_PickUp);
        btn_rework.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardOPeratorRework.this, DashboardOperatorPickUp.class);
                startActivity(i);
                finish();

            }
        });

    }
}
