package com.sip.kelolaapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HospitalPolyclinic extends Activity
{
    private Button btn_order;
    private LinearLayout btn_send;
    Dialog myDialog;
    private TextView judul;
    private ImageView icon_form;
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_form_request);
        myDialog = new Dialog(this);

        judul = (TextView) findViewById(R.id.txt_form_request);
        judul.setText("Form Polyclinic");

        icon_form = (ImageView) findViewById(R.id.img_loc);
        icon_form.setImageResource(R.drawable.ic_polyclinic);
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

                myDialog.setContentView(R.layout.hospital_popup_ordersend);
                btn_send =(LinearLayout) myDialog.findViewById(R.id.popup_success);
                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i = new Intent(HospitalPolyclinic.this, DashboardHospital.class);
                        startActivity(i);
                        finish();
                    }
                });


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
