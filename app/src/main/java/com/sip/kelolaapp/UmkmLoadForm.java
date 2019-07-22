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

public class UmkmLoadForm extends AppCompatActivity {

    private Button btn_struck;
    String receive_no,transaksi_no, transaksi_date,umkm_date,nopolis,umkm_no,umkm_qty,receive_qty,recycleble_qty,end_qty;
    TextView txtUmkmNo, txtUmkmDate;
    EditText edtQtyUmkm;
    ProgressDialog pDialog;
    private static final String TAG = DriverLoadForm.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umkm_load_form);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        txtUmkmNo = (TextView) findViewById(R.id.noUmkm);
        txtUmkmDate = (TextView) findViewById(R.id.dateUmkm);
        edtQtyUmkm = (EditText) findViewById(R.id.qtyUmkm);

        Intent intent = getIntent();
        receive_no = intent.getStringExtra("receive_no");
        transaksi_no = intent.getStringExtra("transaksi_no");
        transaksi_date = intent.getStringExtra("transaksi_date");
        receive_qty = intent.getStringExtra("receive_qty");
        recycleble_qty = intent.getStringExtra("recycleble_qty");
        end_qty = intent.getStringExtra("end_qty");
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        umkm_no = ts;
        umkm_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        txtUmkmNo.setText(umkm_no);
        txtUmkmDate.setText(umkm_date);

        btn_struck = (Button)findViewById(R.id.btn_struck);
        btn_struck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                umkm_qty = edtQtyUmkm.getText().toString();
                sendResult(umkm_no,umkm_date,umkm_qty,receive_no);
                /*Intent i = new Intent(OperatorReceiveForm.this, ReceiveOrder.class);
                startActivity(i);
                finish();*/
            }
        });
    }

    private void sendResult(final String umkm_no,final String umkm_date, final String umkm_qty, final String receive_no)
    {
        // Tag used to cancel the request
        String tag_string_req = "req_senddata";

        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SET_UMKM, new Response.Listener<String>() {

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

                        Intent i = new Intent(UmkmLoadForm.this, UmkmReceiveOrder.class);
                        startActivity(i);
                        finish();

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                "Send UMKM Gagal, Silakan Cek Koneksi Internet Anda !", Toast.LENGTH_LONG).show();
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
                params.put("umkm_no", umkm_no);
                params.put("umkm_date", umkm_date);
                params.put("umkm_qty", umkm_qty);
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
