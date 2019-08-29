package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AuthSignUpRole extends Activity
{
    private Button btn_nextRole;
    private TextView btn_backRole;
    boolean doubleBackToExitPressedOnce = false;
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up_role);
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

