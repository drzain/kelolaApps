package com.sip.kelolaapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HospitalFormRequest extends Activity
{

    private Button btn_order;
    Dialog myDialog;
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_form_request);
        myDialog = new Dialog(this);
    }



    public void Order(View v)
    {
        TextView txtclose;
        myDialog.setContentView(R.layout.hospital_popup_order);
        btn_order = (Button) myDialog.findViewById(R.id.btn_struck);
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
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


}
