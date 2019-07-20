package com.sip.kelolaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OperatorReceiveForm extends AppCompatActivity
{
    private Button btn_struck;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operator_received_form);

        btn_struck = (Button)findViewById(R.id.btn_struck);
        btn_struck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(OperatorReceiveForm.this, ReceiveOrder.class);
                startActivity(i);
                finish();

            }
        });

    }
}
