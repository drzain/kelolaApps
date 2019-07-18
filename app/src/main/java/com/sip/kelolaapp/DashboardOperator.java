package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

public class DashboardOperator extends Activity
{
    private CardView btn_logout, btn_receive, btn_in_progress, btn_packed;
    private SessionManager session;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_operator);
        session = new SessionManager(getApplicationContext());
        action();

    }

    private void action()
    {
        btn_receive = (CardView) findViewById(R.id.process_receive);
        btn_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardOperator.this, ReceiveOrder.class);
                startActivity(i);
            }
        });

        btn_in_progress = (CardView) findViewById(R.id.process_in);
        btn_in_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardOperator.this, ReceiveOrder.class);
                startActivity(i);
            }
        });

        btn_packed = (CardView) findViewById(R.id.process_packed);
        btn_packed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardOperator.this, ReceiveOrder.class);
                startActivity(i);
            }
        });

        btn_logout= (CardView) findViewById(R.id.operator_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                session.setLogin(false);
                Intent i = new Intent(DashboardOperator.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}


