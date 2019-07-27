package com.sip.kelolaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OperatorResultForm extends AppCompatActivity {

    private Button btn_struck, btn_rec, btn_end;
    String receive_no,transaksi_date,receive_qty,recycleble_qty,end_qty;
    TextView txtTransaksiNo, txtTransaksiDate, txtWasteQty;
    EditText edtQtyRecycleble, edtEndWaste;
    ProgressDialog pDialog;
    int qtyRec =0, qtyEnd=0;

    private static final String TAG = OperatorResultForm.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operator_result_form);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        txtTransaksiNo = (TextView) findViewById(R.id.no_result_receive);
        txtTransaksiDate = (TextView) findViewById(R.id.date_result);
        txtWasteQty = (TextView) findViewById(R.id.total_qty);
        edtQtyRecycleble = (EditText) findViewById(R.id.receive_qty);
        edtEndWaste = (EditText) findViewById(R.id.end_qty);
        btn_rec =(Button)findViewById(R.id.rec_qty_decre);
        btn_end =(Button)findViewById(R.id.end_qty_decre);

        Intent intent = getIntent();
        receive_no = intent.getStringExtra("transaksi_no");
        transaksi_date = intent.getStringExtra("transaksi_date");
        receive_qty = intent.getStringExtra("receive_qty");


        txtTransaksiNo.setText(receive_no);
        txtTransaksiDate.setText(transaksi_date);
        txtWasteQty.setText(receive_qty);

        btn_struck = (Button)findViewById(R.id.btn_struck);
        btn_struck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if
                (
                        edtQtyRecycleble.getText().toString().equals("") ||
                                edtQtyRecycleble.getText().toString().equals("0")||
                                    edtEndWaste.getText().toString().equals("")||
                                        edtEndWaste.getText().toString().equals("0")
                )
                {

                        Toast.makeText(getApplicationContext(),
                                "No less than All Zero", Toast.LENGTH_LONG).show();
                /*Intent i = new Intent(OperatorReceiveForm.this, ReceiveOrder.class);
                startActivity(i);
                finish();*/



                } else
                    {
                        recycleble_qty = edtQtyRecycleble.getText().toString();
                        end_qty = edtEndWaste.getText().toString();
                        sendResult(receive_no, recycleble_qty, end_qty);
                }

            }

        });
    }

    private void sendResult(final String receive_no, final String recycleble_qty, final String end_qty)
    {
        // Tag used to cancel the request
        String tag_string_req = "req_senddata";

        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SET_RESULT, new Response.Listener<String>() {

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

                        Intent i = new Intent(OperatorResultForm.this, OperatorPacked.class);
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
                params.put("receive_no", receive_no);
                params.put("recycleble_qty", recycleble_qty);
                params.put("end_qty", end_qty);

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

    public void RecDecre (View view)
    {
        if(qtyRec==0)
        {
            btn_rec.setVisibility(View.INVISIBLE);
            edtQtyRecycleble.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"No less than zero",Toast.LENGTH_SHORT).show();
            return;
        }
        qtyRec = qtyRec - 1;
        display(qtyRec);

    }

    private void display(int number)
    {
        edtQtyRecycleble.setText(""+number);
    }

    public void RecIncre (View view)
    {
        btn_rec.setVisibility(View.VISIBLE);
        edtQtyRecycleble.setVisibility(View.VISIBLE);
        qtyRec = qtyRec + 1;
        display(qtyRec);

    }

    public  void EndDecre (View view)
    {
        if(qtyEnd==0)
        {
            btn_end.setVisibility(View.INVISIBLE);
            edtEndWaste.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"No less than zero",Toast.LENGTH_SHORT).show();
            return;
        }
        qtyEnd = qtyEnd - 1;
        display2(qtyEnd);
    }

    private void display2(int number2)
    {
        edtEndWaste.setText(""+number2);
    }

    public  void  EndIncre (View view)
    {
        btn_end.setVisibility(View.VISIBLE);
        edtEndWaste.setVisibility(View.VISIBLE);
        qtyEnd = qtyEnd + 1;
        display2(qtyEnd);
    }
}
