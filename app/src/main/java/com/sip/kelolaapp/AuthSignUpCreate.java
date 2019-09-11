package com.sip.kelolaapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class AuthSignUpCreate extends Activity
{
    private Button btn_nextCreate;
    private TextView btn_backCreate;
    public String email, user, pass, repass, role, company, companyDet, fullname, mobile;
    public EditText edFullname, edMobile;
    ProgressDialog pDialog;
    private static final String TAG = AuthSignUpCreate.class.getSimpleName();

    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up_create);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        user = intent.getStringExtra("user");
        pass = intent.getStringExtra("pass");
        repass = intent.getStringExtra("repass");
        role = intent.getStringExtra("role");
        company = intent.getStringExtra("company");
        companyDet = intent.getStringExtra("companyDet");

        edFullname=(EditText) findViewById(R.id.fullnameEdit);
        edMobile =(EditText)findViewById(R.id.mobileEdit);


        action();
    }

    private void action()
    {
        btn_nextCreate = (Button) findViewById(R.id.auth_nextBtnCreate);
        btn_nextCreate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                fullname = edFullname.getText().toString();
                mobile= edMobile.getText().toString();

                sendCreate(email,user,pass,role,company, companyDet,fullname,mobile);


                //Intent intent = new Intent(AuthSignUpCreate.this, MainActivity.class);
                //startActivity(intent);
                //finish();

            }
        });

        btn_backCreate = (TextView) findViewById(R.id.auth_backCreate);
        btn_backCreate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AuthSignUpCreate.this, AuthSignUpRole.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void sendCreate(final String email, final String user, final String pass,final String role,final String company,final String companyDet,final String fullname,final String mobile)
    {
        // Tag used to cancel the request
        String tag_string_req = "req_senddata";

        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REG, new Response.Listener<String>() {

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

                        Intent i = new Intent(AuthSignUpCreate.this, MainActivity.class);
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
                params.put("email",email);
                params.put("user",user);
                params.put("pass",pass);
                params.put("repass",repass);
                params.put("role",role);
                params.put("company",company);
                params.put("companyDet", companyDet);
                params.put("fullname", fullname);
                params.put("mobile",mobile);
                Log.e("param ",params.toString());
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
