package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AuthSignUpCreate extends Activity
{
    private Button btn_nextCreate;
    private TextView btn_backCreate;
    boolean doubleBackToExitPressedOnce = false;
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_sign_up_create);
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
                Intent intent = new Intent(AuthSignUpCreate.this, MainActivity.class);
                startActivity(intent);
                finish();

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
}
