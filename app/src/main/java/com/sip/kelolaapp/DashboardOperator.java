package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DashboardOperator extends Activity
{
    private Button btn_logout, btn_receive, btn_in_progress, btn_packed ;
    private SessionManager session;
    EditText edt_infec_emer, edt_infec_poli, edt_infec_nur, edt_infec_far,
            edt_noninfec_emer, edt_noninfec_poli, edt_noninfec_nur, edt_noninfec_far,
            edt_sharp_emer, edt_sharp_poli, edt_sharp_nur, edt_sharp_far;

    Button btn_storage,
            btn_infec_emer, btn_infec_poli, btn_infec_nur, btn_infec_far,
            btn_noninfec_emer, btn_noninfec_poli, btn_noninfec_nur, btn_noninfec_far,
            btn_sharp_emer, btn_sharp_poli, btn_sharp_nur, btn_sharp_far;
    int  qtyInfecemer =0, qtyInfecpoli =0, qtyInfecnur =0, qtyInfecfar =0,
            qtyNonInfecemer =0, qtyNonInfecpoli =0, qtyNonInfecnur =0, qtyNonInfecfar =0,
            qtySharpemer =0, qtySharppoli =0 , qtySharpnur =0, qtySharpfar =0;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_operator_storage);
        session = new SessionManager(getApplicationContext());

        btn_infec_emer = (Button) findViewById(R.id.btn_infec_emer_decrement_id);
        btn_infec_poli = (Button) findViewById(R.id.btn_infec_poli_decrement_id);
        btn_infec_nur = (Button) findViewById(R.id.btn_infec_nur_decrement_id);
        btn_infec_far = (Button) findViewById(R.id.btn_infec_far_decrement_id);

        btn_noninfec_emer = (Button) findViewById(R.id.btn_noninfec_emer_decrement_id);
        btn_noninfec_poli = (Button) findViewById(R.id.btn_noninfec_poli_decrement_id);
        btn_noninfec_nur = (Button) findViewById(R.id.btn_noninfec_nur_decrement_id);
        btn_noninfec_far = (Button) findViewById(R.id.btn_noninfec_far_decrement_id);

        btn_sharp_emer = (Button) findViewById(R.id.btn_sharp_emer_decrement_id);
        btn_sharp_poli = (Button) findViewById(R.id.btn_sharp_poli_decrement_id);
        btn_sharp_nur = (Button) findViewById(R.id.btn_sharp_nur_decrement_id);
        btn_sharp_far = (Button) findViewById(R.id.btn_sharp_far_decrement_id);

        edt_infec_emer =(EditText) findViewById(R.id.edt_infec_emer);
        edt_infec_poli =(EditText) findViewById(R.id.edt_infec_poli);
        edt_infec_nur =(EditText) findViewById(R.id.edt_infec_nur);
        edt_infec_far =(EditText) findViewById(R.id.edt_infec_far);

        edt_noninfec_emer =(EditText) findViewById(R.id.edt_noninfec_emer);
        edt_noninfec_poli =(EditText) findViewById(R.id.edt_noninfec_poli);
        edt_noninfec_nur =(EditText) findViewById(R.id.edt_noninfec_nur);
        edt_noninfec_far =(EditText) findViewById(R.id.edt_noninfec_far);

        edt_sharp_emer = (EditText) findViewById(R.id.edt_sharp_emer);
        edt_sharp_poli = (EditText) findViewById(R.id.edt_sharp_poli);
        edt_sharp_nur = (EditText) findViewById(R.id.edt_sharp_nur);
        edt_sharp_far = (EditText) findViewById(R.id.edt_sharp_far);


        btn_storage = (Button) findViewById(R.id.btn_storage_received);
        btn_storage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(edt_infec_emer.getText().toString().equals("0") && edt_infec_poli.getText().toString().equals("0") && edt_infec_nur.getText().toString().equals("0") && edt_infec_far.getText().toString().equals("0") &&
                   edt_noninfec_emer.getText().toString().equals("0") && edt_noninfec_poli.getText().toString().equals("0") && edt_noninfec_nur.getText().toString().equals("0") && edt_noninfec_far.getText().toString().equals("0") &&
                   edt_sharp_emer.getText().toString().equals("0") && edt_sharp_poli.getText().toString().equals("0") && edt_sharp_nur.getText().toString().equals("0") &&edt_sharp_far.getText().toString().equals("0"))
                {
                    Toast.makeText(getApplicationContext(),
                            "Can't Send Because Zero All!", Toast.LENGTH_LONG).show();
                }
                else {
                    if(edt_infec_emer.getText().toString().equals("") || edt_infec_poli.getText().toString().equals("") || edt_infec_nur.getText().toString().equals("") || edt_infec_far.getText().toString().equals("") ||
                       edt_noninfec_emer.getText().toString().equals("") || edt_noninfec_poli.getText().toString().equals("") || edt_noninfec_nur.getText().toString().equals("") || edt_noninfec_far.getText().toString().equals("") ||
                       edt_sharp_emer.getText().toString().equals("") || edt_sharp_poli.getText().toString().equals("") || edt_sharp_nur.getText().toString().equals("") || edt_sharp_far.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(),
                                "Can't Send Because Null!", Toast.LENGTH_LONG).show();

                    } else
                        {
                            Intent i = new Intent(DashboardOperator.this, DashboardOperatorProcess.class);
                            Toast.makeText(getApplicationContext(),
                                    "Success!!", Toast.LENGTH_LONG).show();
                            startActivity(i);
                            finish();
                    }
                }



            }
        });


    }

    public void InfecEmerDecrement(View view)
    {
        if(qtyInfecemer==0)
        {
            btn_infec_emer.setVisibility(View.INVISIBLE);
            edt_infec_emer.setVisibility(View.INVISIBLE);
            return;
        }
        qtyInfecemer = qtyInfecemer-1 ;
        displayInfecEmer(qtyInfecemer);
    }
    private void displayInfecEmer(int number1)
    {
        edt_infec_emer.setText(""+number1);
    }

    public void InfecEmerIncrement(View view)
    {
        btn_infec_emer.setVisibility(View.VISIBLE);
        edt_infec_emer.setVisibility(View.VISIBLE);
        edt_infec_emer.setBackgroundColor(Color.TRANSPARENT);
        qtyInfecemer = qtyInfecemer+1 ;
        displayInfecEmer(qtyInfecemer);
    }

    public void InfecPoliDecrement(View view)
    {
        if(qtyInfecpoli==0)
        {
            btn_infec_poli.setVisibility(View.INVISIBLE);
            edt_infec_poli.setVisibility(View.INVISIBLE);
            return;
        }
        qtyInfecpoli = qtyInfecpoli-1 ;
        displayInfecPoli(qtyInfecpoli);
    }
    private void displayInfecPoli(int number2)
    {
        edt_infec_poli.setText(""+number2);
    }

    public void InfecPoliIncrement(View view)
    {
        btn_infec_poli.setVisibility(View.VISIBLE);
        edt_infec_poli.setVisibility(View.VISIBLE);
        edt_infec_poli.setBackgroundColor(Color.TRANSPARENT);
        qtyInfecpoli = qtyInfecpoli+1 ;
        displayInfecPoli(qtyInfecpoli);
    }

    public void InfecNurDecrement(View view)
    {
        if(qtyInfecnur==0)
        {
            btn_infec_nur.setVisibility(View.INVISIBLE);
            edt_infec_nur.setVisibility(View.INVISIBLE);
            return;
        }
        qtyInfecnur = qtyInfecnur-1 ;
        displayInfecNur(qtyInfecnur);
    }
    private void displayInfecNur(int number3)
    {
        edt_infec_nur.setText(""+number3);
    }

    public void InfecNurIncrement(View view)
    {
        btn_infec_nur.setVisibility(View.VISIBLE);
        edt_infec_nur.setVisibility(View.VISIBLE);
        edt_infec_nur.setBackgroundColor(Color.TRANSPARENT);
        qtyInfecnur = qtyInfecnur+1 ;
        displayInfecNur(qtyInfecnur);
    }

    public void InfecFarDecrement(View view)
    {
        if(qtyInfecfar==0)
        {
            btn_infec_far.setVisibility(View.INVISIBLE);
            edt_infec_far.setVisibility(View.INVISIBLE);
            return;
        }
        qtyInfecfar = qtyInfecfar-1 ;
        displayInfecFar(qtyInfecfar);
    }
    private void displayInfecFar(int number4)
    {
        edt_infec_far.setText(""+number4);
    }

    public void InfecFarIncrement(View view)
    {
        btn_infec_far.setVisibility(View.VISIBLE);
        edt_infec_far.setVisibility(View.VISIBLE);
        edt_infec_far.setBackgroundColor(Color.TRANSPARENT);
        qtyInfecfar = qtyInfecfar+1 ;
        displayInfecFar(qtyInfecfar);
    }

    public void NonInfecEmerDecrement(View view)
    {
        if(qtyNonInfecemer==0)
        {
            btn_noninfec_emer.setVisibility(View.INVISIBLE);
            edt_noninfec_emer.setVisibility(View.INVISIBLE);
            return;
        }
        qtyNonInfecemer = qtyNonInfecemer-1 ;
        displayNonInfecEmer(qtyNonInfecemer);
    }
    private void displayNonInfecEmer(int number5)
    {
        edt_noninfec_emer.setText(""+number5);
    }

    public void NonInfecEmerIncrement(View view)
    {
        btn_noninfec_emer.setVisibility(View.VISIBLE);
        edt_noninfec_emer.setVisibility(View.VISIBLE);
        edt_noninfec_emer.setBackgroundColor(Color.TRANSPARENT);
        qtyNonInfecemer = qtyNonInfecemer+1 ;
        displayNonInfecEmer(qtyNonInfecemer);
    }

    public void NonInfecPoliDecrement(View view)
    {
        if(qtyNonInfecpoli==0)
        {
            btn_noninfec_poli.setVisibility(View.INVISIBLE);
            edt_noninfec_poli.setVisibility(View.INVISIBLE);
            return;
        }
        qtyNonInfecpoli = qtyNonInfecpoli-1 ;
        displayNonInfecPoli(qtyNonInfecpoli);
    }
    private void displayNonInfecPoli(int number6)
    {
        edt_noninfec_poli.setText(""+number6);
    }

    public void NonInfecPoliIncrement(View view)
    {
        btn_noninfec_poli.setVisibility(View.VISIBLE);
        edt_noninfec_poli.setVisibility(View.VISIBLE);
        edt_noninfec_poli.setBackgroundColor(Color.TRANSPARENT);
        qtyNonInfecpoli = qtyNonInfecpoli+1 ;
        displayNonInfecPoli(qtyNonInfecpoli);
    }

    public void NonInfecNurDecrement(View view)
    {
        if(qtyNonInfecnur==0)
        {
            btn_noninfec_nur.setVisibility(View.INVISIBLE);
            edt_noninfec_nur.setVisibility(View.INVISIBLE);
            return;
        }
        qtyNonInfecnur = qtyNonInfecnur-1 ;
        displayNonInfecNur(qtyNonInfecnur);
    }
    private void displayNonInfecNur(int number7)
    {
        edt_noninfec_nur.setText(""+number7);
    }

    public void NonInfecNurIncrement(View view)
    {
        btn_noninfec_nur.setVisibility(View.VISIBLE);
        edt_noninfec_nur.setVisibility(View.VISIBLE);
        edt_noninfec_nur.setBackgroundColor(Color.TRANSPARENT);
        qtyNonInfecnur = qtyNonInfecnur+1 ;
        displayNonInfecNur(qtyNonInfecnur);
    }

    public void NonInfecFarDecrement(View view)
    {
        if(qtyNonInfecfar==0)
        {
            btn_noninfec_far.setVisibility(View.INVISIBLE);
            edt_noninfec_far.setVisibility(View.INVISIBLE);
            return;
        }
        qtyNonInfecfar = qtyNonInfecfar-1 ;
        displayNonInfecFar(qtyNonInfecfar);
    }
    private void displayNonInfecFar(int number8)
    {
        edt_noninfec_far.setText(""+number8);
    }

    public void NonInfecFarIncrement(View view)
    {
        btn_noninfec_far.setVisibility(View.VISIBLE);
        edt_noninfec_far.setVisibility(View.VISIBLE);
        edt_noninfec_far.setBackgroundColor(Color.TRANSPARENT);
        qtyNonInfecfar = qtyNonInfecfar+1 ;
        displayNonInfecFar (qtyNonInfecfar);
    }


    public void SharpEmerDecrement(View view)
    {
        if(qtySharpemer==0)
        {
            btn_sharp_emer.setVisibility(View.INVISIBLE);
            edt_sharp_emer.setVisibility(View.INVISIBLE);
            return;
        }
        qtySharpemer = qtySharpemer-1 ;
        displaySharpEmer(qtySharpemer);
    }
    private void displaySharpEmer(int number9)
    {
        edt_sharp_emer.setText(""+number9);
    }

    public void SharpEmerIncrement(View view)
    {
        btn_sharp_emer.setVisibility(View.VISIBLE);
        edt_sharp_emer.setVisibility(View.VISIBLE);
        edt_sharp_emer.setBackgroundColor(Color.TRANSPARENT);
        qtySharpemer = qtySharpemer+1 ;
        displaySharpEmer(qtySharpemer);
    }


    public void SharpPoliDecrement(View view)
    {
        if(qtySharppoli==0)
        {
            btn_sharp_poli.setVisibility(View.INVISIBLE);
            edt_sharp_poli.setVisibility(View.INVISIBLE);
            return;
        }
        qtySharppoli = qtySharppoli-1 ;
        displaySharpPoli(qtySharppoli);
    }
    private void displaySharpPoli(int number10)
    {
        edt_sharp_poli.setText(""+number10);
    }

    public void SharpPoliIncrement(View view)
    {
        btn_sharp_poli.setVisibility(View.VISIBLE);
        edt_sharp_poli.setVisibility(View.VISIBLE);
        edt_sharp_poli.setBackgroundColor(Color.TRANSPARENT);
        qtySharppoli = qtySharppoli+1;
        displaySharpPoli(qtySharppoli);
    }


    public void SharpNurDecrement(View view)
    {
        if(qtySharpnur==0)
        {
            btn_sharp_nur.setVisibility(View.INVISIBLE);
            edt_sharp_nur.setVisibility(View.INVISIBLE);
            return;
        }
        qtySharpnur = qtySharpnur-1 ;
        displaySharpNur(qtySharpnur);
    }
    private void displaySharpNur(int number11)
    {
        edt_sharp_nur.setText(""+number11);
    }

    public void SharpNurIncrement(View view)
    {
        btn_sharp_nur.setVisibility(View.VISIBLE);
        edt_sharp_nur.setVisibility(View.VISIBLE);
        edt_sharp_nur.setBackgroundColor(Color.TRANSPARENT);
        qtySharpnur = qtySharpnur+1 ;
        displaySharpNur(qtySharpnur);
    }


    public void SharpFarDecrement(View view)
    {
        if(qtySharpfar==0)
        {
            btn_sharp_far.setVisibility(View.INVISIBLE);
            edt_sharp_far.setVisibility(View.INVISIBLE);
            return;
        }
        qtySharpfar = qtySharpfar-1 ;
        displaySharpFar(qtySharpfar);
    }
    private void displaySharpFar(int number12)
    {
        edt_sharp_far.setText(""+number12);
    }

    public void SharpFarIncrement(View view)
    {
        btn_sharp_far.setVisibility(View.VISIBLE);
        edt_sharp_far.setVisibility(View.VISIBLE);
        edt_sharp_far.setBackgroundColor(Color.TRANSPARENT);
        qtySharpfar = qtySharpfar+1 ;
        displaySharpFar (qtySharpfar);
    }
}


