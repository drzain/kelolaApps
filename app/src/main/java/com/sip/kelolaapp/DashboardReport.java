package com.sip.kelolaapp;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DashboardReport extends AppCompatActivity implements OnChartValueSelectedListener
{

    ProgressDialog pDialog;
    String a, b, c;
    PieChart pieChart1,pieChart2,pieChart3,pieChart4,pieChart5;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_report);

        pieChart1 = (PieChart) findViewById(R.id.barchart);
        pieChart2 = (PieChart) findViewById(R.id.barchart2);
        pieChart3 = (PieChart) findViewById(R.id.barchart3);
        pieChart4 = (PieChart) findViewById(R.id.barchart4);
        pieChart5 = (PieChart) findViewById(R.id.barchart5);
        waste_produce();
        data_proses();
        commodities();
        endwaste();
        recycleble();
    }

    public void waste_produce(){

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
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            //now looping through all the elements of the json array

                            ArrayList<Entry> yvalues = new ArrayList<Entry>();
                            yvalues.add(new Entry(Integer.parseInt(a), 0));
                            yvalues.add(new Entry(Integer.parseInt(b), 1));
                            yvalues.add(new Entry(Integer.parseInt(c), 2));

                            PieDataSet dataSet = new PieDataSet(yvalues, "");

                            ArrayList<String> xVals = new ArrayList<String>();
                            xVals.add("Emergency");
                            xVals.add("Nurse");
                            xVals.add("Polyclinic");

                            PieData data = new PieData(xVals, dataSet);
                            //data.setValueFormatter(new DefaultValueFormatter(0));
                            pieChart1.setData(data);
                            pieChart1.setDescription("Waste Produce");

                            pieChart1.setUsePercentValues(true);
                            pieChart1.setDrawHoleEnabled(true);
                            pieChart1.setTransparentCircleRadius(25f);
                            pieChart1.setHoleRadius(25f);

                            dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                            data.setValueTextSize(8f);
                            data.setValueTextColor(Color.DKGRAY);
                            pieChart1.setOnChartValueSelectedListener(DashboardReport.this);
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

    public void data_proses(){

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_DATA_PROSES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("Refresh",response);
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            a = obj.getString("receive");
                            b = obj.getString("proses");
                            c = obj.getString("packed");
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            //now looping through all the elements of the json array

                            ArrayList<Entry> yvalues = new ArrayList<Entry>();
                            yvalues.add(new Entry(Integer.parseInt(a), 0));
                            yvalues.add(new Entry(Integer.parseInt(b), 1));
                            yvalues.add(new Entry(Integer.parseInt(c), 2));

                            PieDataSet dataSet = new PieDataSet(yvalues, "");

                            ArrayList<String> xVals = new ArrayList<String>();
                            xVals.add("Receive");
                            xVals.add("Proses");
                            xVals.add("Packed");

                            PieData data = new PieData(xVals, dataSet);
                            //data.setValueFormatter(new DefaultValueFormatter(0));
                            pieChart2.setData(data);
                            pieChart2.setDescription("Data Proses");

                            pieChart2.setUsePercentValues(true);
                            pieChart2.setDrawHoleEnabled(true);
                            pieChart2.setTransparentCircleRadius(25f);
                            pieChart2.setHoleRadius(25f);

                            dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                            data.setValueTextSize(8f);
                            data.setValueTextColor(Color.DKGRAY);
                            pieChart2.setOnChartValueSelectedListener(DashboardReport.this);
                            pieChart2.animateXY(1400, 1400);


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

    public void commodities(){

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_DATA_COMMODITIES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("Refresh",response);
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            a = obj.getString("recycleble");
                            b = obj.getString("endwaste");
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            //now looping through all the elements of the json array

                            ArrayList<Entry> yvalues = new ArrayList<Entry>();
                            yvalues.add(new Entry(Integer.parseInt(a), 0));
                            yvalues.add(new Entry(Integer.parseInt(b), 1));

                            PieDataSet dataSet = new PieDataSet(yvalues, "");

                            ArrayList<String> xVals = new ArrayList<String>();
                            xVals.add("Recycleble");
                            xVals.add("End Waste");

                            PieData data = new PieData(xVals, dataSet);
                            //data.setValueFormatter(new DefaultValueFormatter(0));
                            pieChart3.setData(data);
                            pieChart3.setDescription("Commodities");

                            pieChart3.setUsePercentValues(true);
                            pieChart3.setDrawHoleEnabled(true);
                            pieChart3.setTransparentCircleRadius(25f);
                            pieChart3.setHoleRadius(25f);

                            dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                            data.setValueTextSize(8f);
                            data.setValueTextColor(Color.DKGRAY);
                            pieChart3.setOnChartValueSelectedListener(DashboardReport.this);
                            pieChart3.animateXY(1400, 1400);


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

    public void endwaste(){

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_DATA_ENDWASTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("Refresh",response);
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            a = obj.getString("warehouse");
                            b = obj.getString("transport");
                            c = obj.getString("landfill");
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            //now looping through all the elements of the json array

                            ArrayList<Entry> yvalues = new ArrayList<Entry>();
                            yvalues.add(new Entry(Integer.parseInt(a), 0));
                            yvalues.add(new Entry(Integer.parseInt(b), 1));
                            yvalues.add(new Entry(Integer.parseInt(b), 2));

                            PieDataSet dataSet = new PieDataSet(yvalues, "");

                            ArrayList<String> xVals = new ArrayList<String>();
                            xVals.add("Warehouse");
                            xVals.add("Transport");
                            xVals.add("Landfill");

                            PieData data = new PieData(xVals, dataSet);
                            //data.setValueFormatter(new DefaultValueFormatter(0));
                            pieChart4.setData(data);
                            pieChart4.setDescription("End Waste");

                            pieChart4.setUsePercentValues(true);
                            pieChart4.setDrawHoleEnabled(true);
                            pieChart4.setTransparentCircleRadius(25f);
                            pieChart4.setHoleRadius(25f);

                            dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                            data.setValueTextSize(8f);
                            data.setValueTextColor(Color.DKGRAY);
                            pieChart4.setOnChartValueSelectedListener(DashboardReport.this);
                            pieChart4.animateXY(1400, 1400);


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

    public void recycleble(){

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_DATA_RECYCLEBLE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("Refresh",response);
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            a = obj.getString("warehouse");
                            b = obj.getString("umkm");
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            //now looping through all the elements of the json array

                            ArrayList<Entry> yvalues = new ArrayList<Entry>();
                            yvalues.add(new Entry(Integer.parseInt(a), 0));
                            yvalues.add(new Entry(Integer.parseInt(b), 1));

                            PieDataSet dataSet = new PieDataSet(yvalues, "");

                            ArrayList<String> xVals = new ArrayList<String>();
                            xVals.add("Warehouse");
                            xVals.add("UMKM");

                            PieData data = new PieData(xVals, dataSet);
                            //data.setValueFormatter(new DefaultValueFormatter(0));
                            pieChart5.setData(data);
                            pieChart5.setDescription("Recycleble");

                            pieChart5.setUsePercentValues(true);
                            pieChart5.setDrawHoleEnabled(true);
                            pieChart5.setTransparentCircleRadius(25f);
                            pieChart5.setHoleRadius(25f);

                            dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                            data.setValueTextSize(8f);
                            data.setValueTextColor(Color.DKGRAY);
                            pieChart5.setOnChartValueSelectedListener(DashboardReport.this);
                            pieChart5.animateXY(1400, 1400);


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

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

}
