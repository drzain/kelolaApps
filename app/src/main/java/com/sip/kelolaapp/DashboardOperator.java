package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardOperator extends Activity
{
    private LinearLayout btn_logout;
    private SessionManager session;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_operator);
        session = new SessionManager(getApplicationContext());
        action();

    }

    private void action()
    {
        btn_logout= (LinearLayout) findViewById(R.id.operator_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                session.setLogin(false);
                Intent i = new Intent(DashboardOperator.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}


