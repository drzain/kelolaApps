package com.sip.kelolaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardHospital extends AppCompatActivity
{
    private LinearLayout btn_emergency,btn_polyclinic, btn_nurse, btn_logout;
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
        btn_emergency=(LinearLayout)findViewById(R.id.hospital_emergency_order);
        btn_emergency.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
        {
            Intent i = new Intent(DashboardHospital.this, HospitalFormRequest.class);
            startActivity(i);
            finish();

        }
        });

        btn_polyclinic=(LinearLayout)findViewById(R.id.hospital_polyclinic_order);
        btn_polyclinic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardHospital.this, HospitalPolyclinic.class);
                startActivity(i);
                finish();

            }
        });

        btn_nurse=(LinearLayout)findViewById(R.id.hospital_nurse_order);
        btn_nurse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardHospital.this, HospitalNurse.class);
                startActivity(i);
                finish();

            }
        });
    }
}
