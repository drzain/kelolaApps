package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AuthReset extends Activity
{
    private Button resetBtn;
    private TextView resetBack;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_reset);
        action();
    }

    private void action()
    {
        resetBtn = (Button) findViewById(R.id.auth_resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(AuthReset.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        resetBack = (TextView) findViewById(R.id.auth_resetBack);
        resetBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(AuthReset.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }


}
