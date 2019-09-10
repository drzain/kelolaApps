package com.sip.kelolaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AuthReset extends Activity
{
    private Button resetBtn;
    private TextView resetBack;
    public String emailpattern, email;
    EditText ed_email;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_reset);
        emailpattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+";
        ed_email=(EditText) findViewById(R.id.emailReset);


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
                email = ed_email.getText().toString();

                if (email.matches(emailpattern))
                {
                    Intent i = new Intent(AuthReset.this, MainActivity.class);
                    Log.e("Email",""+email);
                    startActivity(i);
                    finish();
                }
                else
                {
                    ed_email.setError("Wrong format email");
                }



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
