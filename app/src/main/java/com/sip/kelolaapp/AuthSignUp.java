package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
    public String email, user, pass, repass, emailpattern;
    private Map<String, String> params;
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up);

        eDemail = (EditText) findViewById(R.id.emailEditText);
        eDuser = (EditText) findViewById(R.id.userEditText);
        eDpass =(EditText) findViewById(R.id.passEditText);
        eDrepass = (EditText) findViewById(R.id.repassEditText);
        emailpattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+";
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
                repass =eDrepass.getText().toString();
                if (email.matches(emailpattern))
                {
                    if (user.equals(""))
                    {
                        eDuser.setError("Input your username");
                    }
                    else
                        {
                        if (pass.equals(""))
                        {
                            eDpass.setError("Input your password");
                        }
                        else
                        {
                            if (repass.equals(pass))
                            {
                                Intent i = new Intent(AuthSignUp.this, AuthSignUpRole.class);
                                i.putExtra("email", email);
                                i.putExtra("user",user);
                                i.putExtra("pass",pass);
                                i.putExtra("repass",repass);
                                Log.e("Email push", ""+email);
                                Log.e("User push", ""+user);
                                Log.e("pass push", ""+pass);
                                Log.e("repass push", ""+repass);
                                startActivity(i);
                                finish();

                            }
                            else
                            {
                                eDrepass.setError("Password is not same");
                            }
                           }
                        }
                }
                else {
                    eDemail.setError("Wrong fromat email");
                }



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
