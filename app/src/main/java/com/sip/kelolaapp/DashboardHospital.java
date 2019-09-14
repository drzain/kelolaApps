package com.sip.kelolaapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DashboardHospital extends AppCompatActivity implements OnChartValueSelectedListener
{
    String a, b, c, d;
    private LinearLayout btn_emergency,btn_polyclinic, btn_nurse, btn_farmasi;
    private CardView btn_inTransit;
    private SessionManager session;
    PieChart pieChart1;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_hospital_new);

        pieChart1 = (PieChart) findViewById(R.id.indicatorChart);
        session = new SessionManager(getApplicationContext());

        dataload();
        action();
    }

    public void dataload(){
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_WASTE_PRODUCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("Refresh",response);
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            a = obj.getString("emergency");
                            b = obj.getString("nurse");
                            c = obj.getString("polyclinic");
                            d = obj.getString("farmasi");
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            //now looping through all the elements of the json array

                            ArrayList<Entry> yvalues = new ArrayList<Entry>();
                            try {
                                yvalues.add(new Entry(Float.parseFloat(a), 0, "Emergency"));
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                            yvalues.add(new Entry(Float.parseFloat(b), 1, "nurse"));
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            try{
                            yvalues.add(new Entry(Float.parseFloat(c), 2, "polyclinic"));
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            try{
                            yvalues.add(new Entry(Float.parseFloat(d), 3, "farmasi"));
                            }catch (Exception e) {
                                e.printStackTrace();
                            }

                            PieDataSet dataSet = new PieDataSet(yvalues, "Waste Produce");

                            ArrayList<String> xVals = new ArrayList<String>();
                            xVals.add("Emergency");
                            xVals.add("Nurse");
                            xVals.add("Polyclinic");
                            xVals.add("Farmasi");

                            //PieData data = new PieData(xVals, dataSet);
                            PieData data = new PieData(xVals,dataSet);
                            pieChart1.setData(data);
                            pieChart1.invalidate();
                            pieChart1.setDescription("");
                            pieChart1.getLegend().setEnabled(false);
                            pieChart1.setUsePercentValues(true);
                            pieChart1.setDrawHoleEnabled(true);
                            pieChart1.setTransparentCircleRadius(25f);
                            pieChart1.setHoleRadius(25f);

                            dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                            data.setValueTextSize(8f);
                            data.setValueTextColor(Color.DKGRAY);
                            data.setValueFormatter(new PercentFormatter());
                            pieChart1.setOnChartValueSelectedListener(DashboardHospital.this);
                            pieChart1.animateXY(1400, 1400);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        try {
                            //Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }catch (Exception x){
                            x.printStackTrace();
                        }
                    }
                }){

        };

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    private void action()
    {
        btn_emergency=(LinearLayout)findViewById(R.id.hospital_emergency_order);
        btn_emergency.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
        {
            Intent i = new Intent(DashboardHospital.this, HospitalFormRequest.class);
            startActivity(i);
            finish();

        }
        });

        btn_polyclinic=(LinearLayout)findViewById(R.id.hospital_polyclinic_order);
        btn_polyclinic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardHospital.this, HospitalPolyclinic.class);
                startActivity(i);
                finish();

            }
        });

        btn_nurse=(LinearLayout)findViewById(R.id.hospital_nurse_order);
        btn_nurse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardHospital.this, HospitalNurse.class);
                startActivity(i);
                finish();

            }
        });
        btn_farmasi=(LinearLayout)findViewById(R.id.hospital_farmasi_order);
        btn_farmasi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardHospital.this, HospitalFarmasi.class);
                startActivity(i);
                finish();

            }
        });

        btn_inTransit=(CardView) findViewById(R.id.hospitalPickupbtn);
        btn_inTransit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(DashboardHospital.this, HospitalInTransit.class);
                startActivity(i);
                finish();

            }
        });
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
