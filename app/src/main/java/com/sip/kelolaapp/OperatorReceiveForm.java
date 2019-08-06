package com.sip.kelolaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class OperatorReceiveForm extends AppCompatActivity
{
    private Button btn_struck, btn_weight;
    String transaksi_no,transaksi_date,waste_qty,receive_qty,receive_no;
    TextView txtTransaksiNo, txtTransaksiDate, txtWasteQty;
    EditText edtQtyReceive;
    ProgressDialog pDialog;
    int qtyWeight =0;
    private static final String TAG = OperatorReceiveForm.class.getSimpleName();
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operator_received_form);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        txtTransaksiNo = (TextView) findViewById(R.id.no_transaksi);
        txtTransaksiDate = (TextView) findViewById(R.id.date_transaksi);
        txtWasteQty = (TextView) findViewById(R.id.total_qty);
        edtQtyReceive = (EditText) findViewById(R.id.receive_qty);
        btn_weight=(Button)findViewById(R.id.receive_qty_decre);

        Intent intent = getIntent();
        transaksi_no = intent.getStringExtra("transaksi_no");
        transaksi_date = intent.getStringExtra("transaksi_date");
        waste_qty = intent.getStringExtra("waste_qty");


        txtTransaksiNo.setText(transaksi_no);
        txtTransaksiDate.setText(transaksi_date);
        txtWasteQty.setText(waste_qty);

        btn_struck = (Button)findViewById(R.id.btn_struck);
        btn_struck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (edtQtyReceive.getText().toString().equals("")|| edtQtyReceive.getText().toString().equals("0"))
                {
                    Toast.makeText(getApplicationContext(),
                        "No less than Zero", Toast.LENGTH_LONG).show();
                }

                else
                {

                receive_qty = edtQtyReceive.getText().toString();
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                receive_no = ts;
                sendReceive(transaksi_no,receive_qty,receive_no);
                /*Intent i = new Intent(OperatorReceiveForm.this, ReceiveOrder.class);
                startActivity(i);
                finish();*/}
            }
        });

    }

    private void sendReceive(final String transaksi_no, final String receive_qty, final String receive_no)
    {
        // Tag used to cancel the request
        String tag_string_req = "req_senddata";

        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_RECEIVE_WASTE, new Response.Listener<String>() {

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

                        Intent i = new Intent(OperatorReceiveForm.this, ReceiveOrder.class);
                        startActivity(i);
                        finish();

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                "Receive Order Gagal, Silakan Cek Koneksi Internet Anda !", Toast.LENGTH_LONG).show();
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
                params.put("transaksi_no", transaksi_no);
                params.put("receive_qty", receive_qty);
                params.put("receive_no", receive_no);

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

    public void WeightDecre(View view)
    {
            if(qtyWeight==0)
            {
                btn_weight.setVisibility(View.INVISIBLE);
                edtQtyReceive.setVisibility(View.INVISIBLE);
                Toast.makeText(this,"No less than zero",Toast.LENGTH_SHORT).show();
                return;
            }
            qtyWeight=qtyWeight-1;
            display(qtyWeight);
    }

    private void display(int number)
    {
        edtQtyReceive.setText(""+number);
    }

    public void WeightIncre(View view)
    {
        int qty = Integer.parseInt(waste_qty);
        btn_weight.setVisibility(View.VISIBLE);
        edtQtyReceive.setVisibility(View.VISIBLE);
        if(qtyWeight==qty)
        {
            Toast.makeText(this,"Max Qty Weight!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        qtyWeight=qtyWeight+1;
        display(qtyWeight);


    }

}
