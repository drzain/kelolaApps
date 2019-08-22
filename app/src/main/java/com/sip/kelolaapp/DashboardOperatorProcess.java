package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class DashboardOperatorProcess extends Activity {
    private Button btn_processing;
    private SessionManager session;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_operator_processor);
        session = new SessionManager(getApplicationContext());

        btn_processing = (Button) findViewById(R.id.btn_op_processing);
        btn_processing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardOperatorProcess.this, DashboardOPeratorRework.class);
                startActivity(i);
                finish();

            }
        });
        //action();

    }

    //   private void action()
    // {

    //   }

}
