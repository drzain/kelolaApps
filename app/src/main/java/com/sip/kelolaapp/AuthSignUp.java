package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AuthSignUp extends Activity
{
    private Button btn_next;
    private TextView btn_back;
    boolean doubleBackToExitPressedOnce = false;
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up);
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
