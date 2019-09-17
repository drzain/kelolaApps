package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DashboardOperatorProcess extends Activity {
    private Button btn_processing;
    private SessionManager session;
    EditText edt_infec, edt_noninfec, edt_sharp;
    Button btn_infec, btn_noninfec, btn_sharp;
    int qtyInfec =0, qtyNonInfec =0, qtySharp =0;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_operator_processor);
        session = new SessionManager(getApplicationContext());

        btn_infec = (Button) findViewById(R.id.infec_decrement_pro);
        btn_noninfec = (Button) findViewById(R.id.noninfec_decrment_pro);
        btn_sharp =(Button) findViewById(R.id.sharp_decrement_pro);

        edt_infec =(EditText) findViewById(R.id.edt_infec_pro);
        edt_noninfec=(EditText) findViewById(R.id.edt_noninfec_pro);
        edt_sharp = (EditText) findViewById(R.id.edt_sharp_pro);

        btn_processing = (Button) findViewById(R.id.btn_op_processing);
        btn_processing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(edt_infec.getText().toString().equals("0") && edt_noninfec.getText().toString().equals("0") && edt_sharp.getText().toString().equals("0"))
                {
                    Toast.makeText(getApplicationContext(),
                            "Can't Send Because Zero All!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (edt_infec.getText().toString().equals("") || edt_noninfec.getText().toString().equals("") || edt_sharp.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Can't Send Because Null!", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Success!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(DashboardOperatorProcess.this, DashboardOPeratorRework.class);
                        startActivity(i);
                        finish();

                    }
                }


            }
        });


    }


    public void InfecDecrement(View view)
    {
        if(qtyInfec==0)
        {
            btn_infec.setVisibility(View.INVISIBLE);
            edt_infec.setVisibility(View.INVISIBLE);
            return;
        }
        qtyInfec = qtyInfec-1 ;
        displayInfec(qtyInfec);
    }
    private void displayInfec(int number)
    {

        edt_infec.setText(""+number);

    }

    public void InfecIncrement(View view)
    {
        btn_infec.setVisibility(View.VISIBLE);
        edt_infec.setVisibility(View.VISIBLE);
        edt_infec.setBackgroundColor(Color.TRANSPARENT);
        qtyInfec = qtyInfec+1 ;
        displayInfec(qtyInfec);

    }

    public void NonInfecDecrement(View view)
    {
        if(qtyNonInfec==0)
        {
            btn_noninfec.setVisibility(View.INVISIBLE);
            edt_noninfec.setVisibility(View.INVISIBLE);
            return;
        }
        qtyNonInfec = qtyNonInfec-1 ;
        displayNonInfec(qtyNonInfec);
    }

    private void displayNonInfec(int number)
    {

        edt_noninfec.setText(""+number);

    }

    public void NonInfecIncrement(View view)
    {
        btn_noninfec.setVisibility(View.VISIBLE);
        edt_noninfec.setVisibility(View.VISIBLE);
        edt_noninfec.setBackgroundColor(Color.TRANSPARENT);
        qtyNonInfec = qtyNonInfec+1 ;
        displayNonInfec(qtyNonInfec);

    }

    public void SharpDecrement(View view)
    {
        if(qtySharp==0)
        {
            btn_sharp.setVisibility(View.INVISIBLE);
            edt_sharp.setVisibility(View.INVISIBLE);
            return;
        }
        qtySharp = qtySharp-1 ;
        displaySharp(qtySharp);

    }

    private void displaySharp(int number)
    {

        edt_sharp.setText(""+number);

    }

    public void SharpIncrement(View view)
    {
        btn_sharp.setVisibility(View.VISIBLE);
        edt_sharp.setVisibility(View.VISIBLE);
        edt_sharp.setBackgroundColor(Color.TRANSPARENT);
        qtySharp = qtySharp+1 ;
        displaySharp(qtySharp);
    }
}
