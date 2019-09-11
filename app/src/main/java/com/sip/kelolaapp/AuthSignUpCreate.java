package com.sip.kelolaapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    public String email, username, password, repass, role, company_tipe, company_name, fullname, phone;
    public EditText edFullname, edMobile;
    ProgressDialog pDialog;
    CheckBox checkbox;
    private static final String TAG = AuthSignUpCreate.class.getSimpleName();

    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up_create);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        email = getIntent().getStringExtra("email");
        username     = getIntent().getStringExtra("user");
        password = getIntent().getStringExtra("pass");
        repass = getIntent().getStringExtra("repass");
        role = getIntent().getStringExtra("role");
        company_tipe = getIntent().getStringExtra("company");
        company_name = getIntent().getStringExtra("companyDet");
        Log.e("Email get", ""+email);
        Log.e("User get", ""+username);
        Log.e("Pass get",""+ password);
        Log.e("Role get", ""+role);
        Log.e("Company get", ""+company_tipe);
        Log.e("Company Detail get", ""+company_name);
        edFullname=(EditText) findViewById(R.id.fullnameEdit);
        edMobile =(EditText)findViewById(R.id.mobileEdit);
        checkbox =(CheckBox)findViewById(R.id.authcheckbox);



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
                phone= edMobile.getText().toString();
                if(fullname.equals(""))
                {
                    edFullname.setError("Put Your Fullname, Please!!!");
                }
                else
                {
                    if(phone.equals(""))
                    {
                        edMobile.setError("Put Your Phone Number , Please!!!");
                    }
                    else
                    {
                        if(checkbox.isChecked())
                        {
                            sendCreate(email,username,password,role,company_tipe, company_name,fullname,phone);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Checked, if you are not robot", Toast.LENGTH_LONG).show();
                        }
                    }
                }





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
                params.put("username",username);
                params.put("password",password);
                params.put("role",role);
                params.put("company_tipe",company_tipe);
                params.put("company_name", company_name);
                params.put("fullname", fullname);
                params.put("mobile",mobile);
                Log.e("param ",params.toString());
                params.put("phone",phone);
                Log.e("email push",""+email);
                Log.e("user push",""+username);
                Log.e("role push",""+role);
                Log.e("pass push ",""+password);
                Log.e("company push",""+company_tipe);
                Log.e("company detail push",""+company_name);
                Log.e("fullname push",""+fullname);
                Log.e("mobile push",""+phone);
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
