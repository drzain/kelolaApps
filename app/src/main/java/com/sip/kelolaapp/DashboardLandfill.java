package com.sip.kelolaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardLandfill extends AppCompatActivity {

    private LinearLayout btn_logout;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_landfill);
        action();
        session = new SessionManager(getApplicationContext());
    }

    private void action()
    {
        btn_logout= (LinearLayout) findViewById(R.id.landfill_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                session.setLogin(false);
                Intent i = new Intent(DashboardLandfill.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
