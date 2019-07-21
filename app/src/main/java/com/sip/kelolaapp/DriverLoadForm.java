package com.sip.kelolaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DriverLoadForm extends AppCompatActivity {

    private Button btn_struck;
    String receive_no,transaksi_no, transaksi_date,transport_date,nopolis,transport_no,transport_qty,receive_qty,recycleble_qty,end_qty;
    TextView txtTransportNo, txtTransportDate, txtTransportDrop;
    EditText edtQtyTransport;
    Spinner spNoPolis;
    ProgressDialog pDialog;
    private static final String TAG = DriverLoadForm.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_form_load);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        txtTransportNo = (TextView) findViewById(R.id.noTransport);
        txtTransportDate = (TextView) findViewById(R.id.dateTransport);
        txtTransportDrop = (TextView) findViewById(R.id.dropTransport);
        edtQtyTransport = (EditText) findViewById(R.id.qtyTransport);
        spNoPolis = (Spinner) findViewById(R.id.nopolis);

        Intent intent = getIntent();
        receive_no = intent.getStringExtra("receive_no");
        transaksi_no = intent.getStringExtra("transaksi_no");
        transaksi_date = intent.getStringExtra("transaksi_date");
        receive_qty = intent.getStringExtra("receive_qty");
        recycleble_qty = intent.getStringExtra("recycleble_qty");
        end_qty = intent.getStringExtra("end_qty");
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        transport_no = ts;
        transport_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        txtTransportNo.setText(transport_no);
        txtTransportDate.setText(transport_date);

        btn_struck = (Button)findViewById(R.id.btn_struck);
        btn_struck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                transport_qty = edtQtyTransport.getText().toString();
                nopolis = spNoPolis.getSelectedItem().toString();
                sendResult(transport_no,transport_date,transport_qty,nopolis,receive_no);
                /*Intent i = new Intent(OperatorReceiveForm.this, ReceiveOrder.class);
                startActivity(i);
                finish();*/
            }
        });

    }

    private void sendResult(final String transport_no,final String transport_date, final String transport_qty, final String nopolis,final String receive_no)
    {
        // Tag used to cancel the request
        String tag_string_req = "req_senddata";

        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SET_LOAD, new Response.Listener<String>() {

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

                        Intent i = new Intent(DriverLoadForm.this, DriverReceiveOrder.class);
                        startActivity(i);
                        finish();

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                "Send Result Gagal, Silakan Cek Koneksi Internet Anda !", Toast.LENGTH_LONG).show();
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
                params.put("transport_no", transport_no);
                params.put("transport_date", transport_date);
                params.put("transport_qty", transport_qty);
                params.put("nopolis", nopolis);
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
}
