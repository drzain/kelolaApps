package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AuthSignUp extends Activity
{
    private Button btn_next;
    private TextView btn_back;
    boolean doubleBackToExitPressedOnce = false;
    public EditText eDemail, eDuser, eDpass, eDrepass;
    public String email, user, pass, repass;
    private Map<String, String> params;
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up);

        eDemail = (EditText) findViewById(R.id.emailEditText);
        eDuser = (EditText) findViewById(R.id.userEditText);
        eDpass =(EditText) findViewById(R.id.passEditText);
        eDrepass = (EditText) findViewById(R.id.repassEditText);
        action();
    }

    private void action()
    {
        btn_next = (Button) findViewById(R.id.auth_nextBtn);
        btn_next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email = eDemail.getText().toString();
                user = eDuser.getText().toString();
                pass = eDpass.getText().toString();
                repass =eDpass.getText().toString();

                Map<String, String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("user",user);
                params.put("pass",pass);
                params.put("repass",repass);

                Intent intent = new Intent(AuthSignUp.this, AuthSignUpRole.class);
                startActivity(intent);
                finish();


            }
        });

        btn_back = (TextView) findViewById(R.id.auth_back);
        btn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AuthSignUp.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


}
