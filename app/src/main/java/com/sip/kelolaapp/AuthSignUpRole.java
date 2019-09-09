package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AuthSignUpRole extends Activity
{
    private Button btn_nextRole;
    private TextView btn_backRole;
    public Spinner roleUser, comUser;
    public String email, user, pass, repass, role, company, companyDet;
    public EditText eDcompany;


    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up_role);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        user = intent.getStringExtra("user");
        pass = intent.getStringExtra("pass");
        repass = intent.getStringExtra("repass");

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

                Map<String, String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("user",user);
                params.put("pass",pass);
                params.put("repass",repass);
                params.put("role",role);
                params.put("company",company);
                params.put("companyDet", companyDet);

                Intent intent = new Intent(AuthSignUpRole.this, AuthSignUpCreate.class);
                startActivity(intent);
                finish();

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

