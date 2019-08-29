package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class DashboardOperatorPickUp extends Activity
{
    private SessionManager session;
    private Button btn_pickUp;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_operator_pickup);
        session = new SessionManager(getApplicationContext());
        action();
    }

    private void action()
    {
        btn_pickUp=(Button) findViewById(R.id.pickUpbtn);
        btn_pickUp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardOperatorPickUp.this, DashboardActivityNav.class);
                startActivity(i);
                finish();

            }
        });

    }
}
