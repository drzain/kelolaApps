package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HospitalInTransit extends Activity
{
    private Button btn_drop;
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_hospital_in_transit);

        btn_drop=(Button) findViewById(R.id.btn_transit_drop);
        btn_drop.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(HospitalInTransit.this, DashboardHospital.class);
                startActivity(i);
                finish();
            }

        });


    }


    public void InfectionDecrement2(View view) {
    }

    public void InfectionIncrement2(View view) {
    }

    public void NonInfecDecrement2(View view) {
    }

    public void NonInfecIncrement2(View view) {
    }

    public void SharpDecrement2(View view) {
    }

    public void SharpIncrement2(View view) {
    }
}
