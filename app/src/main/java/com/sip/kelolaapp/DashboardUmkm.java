package com.sip.kelolaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardUmkm extends AppCompatActivity {

    private LinearLayout btn_logout;
    private SessionManager session;
    private CardView btn_receive, btn_umkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_umkm);
        action();
        session = new SessionManager(getApplicationContext());
    }

    private void action()
    {
        btn_umkm = (CardView) findViewById(R.id.commodities_umkm);
        btn_umkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardUmkm.this, UmkmReceiveOrder.class);
                startActivity(i);
            }
        });

        btn_receive = (CardView) findViewById(R.id.commodities_received);
        btn_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardUmkm.this, CommoditiesRecevied.class);
                startActivity(i);
            }
        });

    }
}
