package com.sip.kelolaapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HospitalFormRequest extends Activity
{

    private Button btn_order;
    private LinearLayout btn_send;
    Dialog myDialog;
    private static final String TAG = HospitalFormRequest.class.getSimpleName();
    private TextView judul;
    private ImageView icon_form;
    String infus, syringe, nacl, uniqueid;
    EditText edtItem1, edtItem2, edtItem3;
    TextView infus_rekap, syringe_rekap, nacl_rekap;
    ProgressDialog pDialog;
    int qtyInfus =0;
    int qtySyringe =0;
    int qtyNacl=0;
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_form_request);
        myDialog = new Dialog(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        edtItem1 = (EditText) findViewById(R.id.edt_item1);
        edtItem2 = (EditText) findViewById(R.id.edt_item2);
        edtItem3 = (EditText) findViewById(R.id.edt_item3);

        judul = (TextView) findViewById(R.id.txt_form_request);
        judul.setText("Form Emergency");

        icon_form = (ImageView) findViewById(R.id.img_loc);
        icon_form.setImageResource(R.drawable.ic_emergeny);
    }

    public void Order(View v)
    {
        TextView txtclose;
        myDialog.setContentView(R.layout.hospital_popup_order);
        infus_rekap = (TextView) myDialog.findViewById(R.id.infus_rekap);
        syringe_rekap = (TextView) myDialog.findViewById(R.id.syringe_rekap);
        nacl_rekap = (TextView) myDialog.findViewById(R.id.nacl_rekap);

        infus = edtItem1.getText().toString();
        syringe = edtItem2.getText().toString();
        nacl = edtItem3.getText().toString();
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        uniqueid = ts;

        infus_rekap.setText(infus);
        syringe_rekap.setText(syringe);
        nacl_rekap.setText(nacl);

        btn_order = (Button) myDialog.findViewById(R.id.btn_struck);
        btn_order.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //myDialog.dismiss();
                Log.e("nilai input",infus+" "+syringe+" "+nacl+" "+uniqueid);
                sendData(infus,syringe,nacl,uniqueid);
                /*myDialog.setContentView(R.layout.hospital_popup_ordersend);

                btn_send =(LinearLayout) myDialog.findViewById(R.id.popup_success);
                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i = new Intent(HospitalFormRequest.this, DashboardHospital.class);
                        startActivity(i);
                        finish();
                    }
                });*/


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

    private void sendData(final String infusx, final String syringex, final String naclx, final String uniqueidx)
    {
        // Tag used to cancel the request
        String tag_string_req = "req_senddata";

        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_ORDER_WASTE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Save Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e(TAG, "obj: " + jObj.toString());
                    String error = jObj.getString("status");
                    Log.e(TAG, "obj: " + error);
                    // Check for error node in json
                    if (error.equals("1")) {
                        myDialog.setContentView(R.layout.hospital_popup_ordersend);

                        btn_send =(LinearLayout) myDialog.findViewById(R.id.popup_success);
                        btn_send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                Intent i = new Intent(HospitalFormRequest.this, DashboardHospital.class);
                                startActivity(i);
                                finish();
                            }
                        });
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                "Kirim Order Gagal, Silakan Cek Koneksi Internet Anda !", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                /*Intent intent = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();*/
                Log.e(TAG, "Send Data Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Send Data Error", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("infus", infusx);
                params.put("syringe", syringex);
                params.put("nacl", naclx);
                params.put("tipe","emergency");
                params.put("uniqueid", uniqueidx);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    public void InfusionDecrement(View view)
    {
        if(qtyInfus==0){
            Toast.makeText(this,"pesanan minimal 0",Toast.LENGTH_SHORT).show();
            return;
        }
        qtyInfus = qtyInfus-1 ;
        display(qtyInfus);
    }

    private void display(int number)
    {

        edtItem1.setText(""+number);

    }

    public void InfusionIncrement(View view)
    {
        if(qtyInfus==1000){
            Toast.makeText(this,"pesanan maximal 1000",Toast.LENGTH_SHORT).show();
            return;
        }
        qtyInfus = qtyInfus+1 ;
        display(qtyInfus);

    }



    public void SyringeIncrement(View view)
    {
        if(qtySyringe==1000){
            Toast.makeText(this,"pesanan maximal 1000",Toast.LENGTH_SHORT).show();
            return;
        }
        qtySyringe = qtySyringe+1 ;
        display2(qtySyringe);
    }

    private void display2(int number2)
    {
        edtItem2.setText(""+number2);
    }

    public void SyringeDecrement(View view)
    {
        if(qtySyringe==0){
            Toast.makeText(this,"pesanan minimal 0",Toast.LENGTH_SHORT).show();
            return;
        }
        qtySyringe = qtySyringe-1 ;
        display2(qtySyringe);
    }

    public void NaclIncrement(View view)
    {
        if(qtyNacl==1000){
            Toast.makeText(this,"pesanan maximal 1000",Toast.LENGTH_SHORT).show();
            return;
        }
        qtyNacl = qtyNacl+1 ;
        display3(qtyNacl);
    }

    private void display3(int number3)
    {
        edtItem3.setText(""+number3);
    }
    public void NaclDecrement(View view)
    {
        if(qtyNacl==0){
            Toast.makeText(this,"pesanan minimal 0",Toast.LENGTH_SHORT).show();
            return;
        }
        qtyNacl = qtyNacl-1 ;
        display3(qtyNacl);
    }
}
