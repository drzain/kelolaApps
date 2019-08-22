package com.sip.kelolaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DashboardActivityNew extends Fragment
{
    private LinearLayout btn_hospital,btn_operator, btn_driver, btn_tracker, btn_logout, btn_landfill, btn_report;
    private SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dashboard_new, container, false);

        session = new SessionManager(getActivity().getApplicationContext());
        String nama = session.isNama();
        TextView nama_dashboard =(TextView) view.findViewById(R.id.textDashboard);
        nama_dashboard.setText("Hi, "+nama);




        btn_hospital = (LinearLayout) view.findViewById(R.id.hospital);
        btn_hospital.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DashboardHospital.class);
                getActivity().startActivity(i);

            }
        });

        btn_operator = (LinearLayout) view.findViewById(R.id.operator);
        btn_operator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DashboardOperator.class);
                getActivity().startActivity(i);

            }
        });

        btn_driver = (LinearLayout) view.findViewById(R.id.driver);
        btn_driver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DashboardDriver.class);
                getActivity().startActivity(i);

            }
        });

        btn_tracker = (LinearLayout) view.findViewById(R.id.tracker);
        btn_tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getActivity(), DashboardTracker.class);
                getActivity().startActivity(i);
            }
        });
/*
        btn_report = (LinearLayout) view.findViewById(R.id.admin_live_dashboard);
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DashboardReport.class);
                getActivity().startActivity(i);
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

        btn_logout = (LinearLayout) view.findViewById(R.id.logout_admin);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setLogin(false);
                Intent i = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });*/

        return  view;


    }

}
