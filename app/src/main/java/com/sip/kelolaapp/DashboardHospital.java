package com.sip.kelolaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardHospital extends AppCompatActivity
{
    private LinearLayout btn_order, btn_logout;
    private SessionManager session;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_hospital);

        session = new SessionManager(getApplicationContext());

        action();
    }

    private void action()
    {
        btn_order=(LinearLayout)findViewById(R.id.hospital_order);
        btn_order.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
        {
            Intent i = new Intent(DashboardHospital.this, HospitalFormRequest.class);
            startActivity(i);
            finish();

        }
        });

        btn_logout=(LinearLayout)findViewById(R.id.hospital_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                session.setLogin(false);
                Intent i = new Intent(DashboardHospital.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
