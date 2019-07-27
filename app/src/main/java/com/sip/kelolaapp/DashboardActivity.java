package com.sip.kelolaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout btn_hospital,btn_operator, btn_driver, btn_umkm, btn_logout, btn_landfill, btn_report;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        session = new SessionManager(getApplicationContext());
        String nama = session.isNama();
        TextView nama_dashboard =(TextView) findViewById(R.id.textDashboard);
        nama_dashboard.setText("Hi, "+nama);

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

        btn_report = (LinearLayout) findViewById(R.id.admin_live_dashboard);
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, DashboardReport.class);
                startActivity(i);
            }
        });

      /*  btn_landfill = (LinearLayout) findViewById(R.id.landfill);
        btn_landfill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, DashboardLandfill.class);
                startActivity(i);
            }
        });

        btn_vendor = (LinearLayout) findViewById(R.id.vendor);
        btn_vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, DashboardVendor.class);
                startActivity(i);
            }
        });
*/
        btn_logout = (LinearLayout) findViewById(R.id.logout_admin);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setLogin(false);
                Intent i = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
