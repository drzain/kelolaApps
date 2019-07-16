package com.sip.kelolaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardHospital extends AppCompatActivity
{
    private LinearLayout btn_form_request, btn_checkout_request;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_hospital);
        action();
    }

    private void action()
    {
        btn_form_request=(LinearLayout)findViewById(R.id.form_request);
        btn_form_request.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
        {
            Intent i = new Intent(DashboardHospital.this, HospitalFormRequest.class);
            startActivity(i);

        }
        });

        btn_checkout_request=(LinearLayout)findViewById(R.id.checkout_request);
        btn_checkout_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardHospital.this, HospitalFormRequest.class);
                startActivity(i);
            }
        });
    }
}
