package com.sip.kelolaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout btn_hospital,btn_operator, btn_driver, btn_umkm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        action();
    }

    private void action()
    {
        btn_hospital = (LinearLayout) findViewById(R.id.hospital);
        btn_hospital.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardActivity.this, DashboardHospital.class);
                startActivity(i);

            }
        });

        btn_operator = (LinearLayout) findViewById(R.id.operator);
        btn_operator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardActivity.this, DashboardOperator.class);
                startActivity(i);

            }
        });

        btn_driver = (LinearLayout) findViewById(R.id.driver);
        btn_driver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardActivity.this, DashboardDriver.class);
                startActivity(i);

            }
        });

        btn_umkm = (LinearLayout) findViewById(R.id.umkm);
        btn_umkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, DashboardUmkm.class);
                startActivity(i);
            }
        });


    }
}
