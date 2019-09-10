package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AuthSignUpRole extends Activity
{
    private Button btn_nextRole;
    private TextView btn_backRole;
    public Spinner roleUser, comUser;
    public String email, user, pass, repass1, role, company, companyDet;
    public EditText eDcompany;


    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up_role);


        email = getIntent().getStringExtra("email");
        user = getIntent().getStringExtra("user");
        pass = getIntent().getStringExtra("pass");
        Log.e("Email get",""+ email);
        Log.e("User get",""+ user);
        Log.e("Pass get", ""+pass);

        roleUser = (Spinner) findViewById(R.id.roleSpin);
        comUser = (Spinner) findViewById(R.id.comSpin);
        eDcompany =(EditText) findViewById(R.id.comEditNm);
        action();

    }

    private void action()
    {
       btn_nextRole = (Button) findViewById(R.id.auth_nextBtnRole);
        btn_nextRole.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                role = roleUser.getSelectedItem().toString();
                company = comUser.getSelectedItem().toString();
                companyDet = eDcompany.getText().toString();
                if (role.equals("--Selected--"))
                {
                    Toast.makeText(getApplicationContext(),
                            "Must Select Role", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(company.equals("--Selected--"))
                    {
                        Toast.makeText(getApplicationContext(),
                                "Must Select Company Type", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(companyDet.equals(""))
                        {
                            /*Toast.makeText(getApplicationContext(),
                                    "Entry Your Company Name", Toast.LENGTH_LONG).show();*/
                            eDcompany.setError("Entry Your Company Name");
                        }
                        else
                        {
                            Intent i = new Intent(AuthSignUpRole.this, AuthSignUpCreate.class);
                            i.putExtra("email",email);
                            i.putExtra("user",user);
                            i.putExtra("pass",pass);
                            i.putExtra("role",role);
                            i.putExtra("company",company);
                            i.putExtra("companyDet", companyDet);
                            Log.e("Email push", ""+email);
                            Log.e("User push", ""+user);
                            Log.e("Pass push",""+ pass);
                            Log.e("Role push", ""+role);
                            Log.e("Company push", ""+company);
                            Log.e("Company Detail push", ""+companyDet);

                            startActivity(i);
                            finish();

                        }


                    }


                }





            }
        });

        btn_backRole = (TextView) findViewById(R.id.auth_backRole);
        btn_backRole.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AuthSignUpRole.this, AuthSignUp.class);
                startActivity(intent);
                finish();

            }
        });

    }


}

