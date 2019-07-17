package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class HospitalFormRequest extends Activity
{

    private Button btn_order;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_form_request);
        action();
    }

    private void action()
    {
        btn_order=(Button)findViewById(R.id.btn_order_form);
        btn_order.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(HospitalFormRequest.this, DashboardHospital.class);
                startActivity(i);
                finish();

            }
        });

    }


}
