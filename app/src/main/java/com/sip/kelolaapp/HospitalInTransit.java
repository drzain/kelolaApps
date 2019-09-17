package com.sip.kelolaapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HospitalInTransit extends Activity
{

    private Button btn_drop;
    Dialog myDialog;
    ProgressDialog pDialog;
    EditText edt_infec, edt_noninfec, edt_sharp;
    Button btn_infec, btn_noninfec, btn_sharp;
    int qtyInfec =0, qtyNonInfec =0, qtySharp =0;
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_hospital_in_transit);
        myDialog = new Dialog(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btn_infec = (Button) findViewById(R.id.infec_decrement_id2);
        btn_noninfec = (Button) findViewById(R.id.noninfec_decrment_id2);
        btn_sharp =(Button) findViewById(R.id.sharp_decrement_id2);

        edt_infec =(EditText) findViewById(R.id.edt_infec);
        edt_noninfec=(EditText) findViewById(R.id.edt_noninfec);
        edt_sharp = (EditText) findViewById(R.id.edt_sharp);


        btn_drop=(Button) findViewById(R.id.btn_transit_drop);
        btn_drop.setOnClickListener(new View.OnClickListener()
        {

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

                    } else
                        {

                            Intent i = new Intent(HospitalInTransit.this, DashboardHospital.class);
                            Toast.makeText(getApplicationContext(),
                                    "Success!", Toast.LENGTH_LONG).show();
                            startActivity(i);
                            finish();

                    }
                }


            }

        });


    }


    public void InfectionDecrement2(View view)
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

    public void InfectionIncrement2(View view)
    {
        btn_infec.setVisibility(View.VISIBLE);
        edt_infec.setVisibility(View.VISIBLE);
        edt_infec.setBackgroundColor(Color.TRANSPARENT);
        qtyInfec = qtyInfec+1 ;
        displayInfec(qtyInfec);

    }

    public void NonInfecDecrement2(View view)
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

    public void NonInfecIncrement2(View view)
    {
        btn_noninfec.setVisibility(View.VISIBLE);
        edt_noninfec.setVisibility(View.VISIBLE);
        edt_noninfec.setBackgroundColor(Color.TRANSPARENT);
        qtyNonInfec = qtyNonInfec+1 ;
        displayNonInfec(qtyNonInfec);

    }

    public void SharpDecrement2(View view)
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

    public void SharpIncrement2(View view)
    {
        btn_sharp.setVisibility(View.VISIBLE);
        edt_sharp.setVisibility(View.VISIBLE);
        edt_sharp.setBackgroundColor(Color.TRANSPARENT);
        qtySharp = qtySharp+1 ;
        displaySharp(qtySharp);
    }
}
