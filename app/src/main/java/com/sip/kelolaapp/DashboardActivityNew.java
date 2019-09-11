package com.sip.kelolaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivityNew extends Fragment
{
    private LinearLayout btn_hospital,btn_operator, btn_driver, btn_tracker, btn_logout, btn_landfill, btn_report;
    private SessionManager session;
    ImageView gambar1,gambar2;
    TextView judul1,judul2;
    CardView news1, news2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dashboard_new, container, false);

        session = new SessionManager(getActivity().getApplicationContext());
        String nama = session.isNama();
        TextView nama_dashboard =(TextView) view.findViewById(R.id.textDashboard);
        gambar1 = (ImageView) view.findViewById(R.id.newsThumbnailImageView);
        judul1 = (TextView) view.findViewById(R.id.newsTitleTextView);
        gambar2 = (ImageView) view.findViewById(R.id.newsThumbnailImageView2);
        judul2 = (TextView) view.findViewById(R.id.newsTitleTextView2);
        news1 = (CardView) view.findViewById(R.id.new1);
        news2 = (CardView) view.findViewById(R.id.new2);
        nama_dashboard.setText("Hi, "+nama);

        LoadAsset();

        btn_hospital = (LinearLayout) view.findViewById(R.id.hospital);
        btn_hospital.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DashboardHospital.class);
                getActivity().startActivity(i);

            }
        });

        btn_operator = (LinearLayout) view.findViewById(R.id.operator);
        btn_operator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DashboardOperator.class);
                getActivity().startActivity(i);

            }
        });

        btn_driver = (LinearLayout) view.findViewById(R.id.driver);
        btn_driver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DashboardDriver.class);
                getActivity().startActivity(i);

            }
        });

        btn_tracker = (LinearLayout) view.findViewById(R.id.tracker);
        btn_tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getActivity(), DashboardTracker.class);
                getActivity().startActivity(i);
            }
        });
/*
        btn_report = (LinearLayout) view.findViewById(R.id.admin_live_dashboard);
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DashboardReport.class);
                getActivity().startActivity(i);
            }
        });

      /*  btn_landfill = (LinearLayout) findViewById(R.id.landfill);
        btn_landfill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, DashboardLandfill.class);
                startActivity(i);
            }
        });

        btn_vendor = (LinearLayout) findViewById(R.id.vendor);
        btn_vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, DashboardVendor.class);
                startActivity(i);
            }
        });

        btn_logout = (LinearLayout) view.findViewById(R.id.logout_admin);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setLogin(false);
                Intent i = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });*/

        return  view;


    }

    public void LoadAsset(){
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_BERITA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("Refresh",response);
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            JSONArray items = obj.getJSONArray("items");
                            for(int i = 1; i <= 2; i++)
                            {
                                final JSONObject object = items.getJSONObject(i);
                                if(i == 1) {
                                    judul1.setText(object.getString("title"));
                                    final String weblink = object.getString("link");
                                    Picasso.with(getContext()).load(object.getString("thumbnail")).into(gambar1);
                                    news1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getActivity(), WebActivity.class);
                                            intent.putExtra("linkweb",weblink);
                                            startActivity(intent);
                                        }
                                    });
                                }else{
                                    judul2.setText(object.getString("title"));
                                    Picasso.with(getContext()).load(object.getString("thumbnail")).into(gambar2);
                                    final String weblink = object.getString("link");
                                    news2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getActivity(), WebActivity.class);
                                            intent.putExtra("linkweb",weblink);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            //now looping through all the elements of the json array
                            /*ArrayList data = new ArrayList<DataBerita>();
                            Log.e("data berita",obj.toString());
                            for (int i = 0; i < obj.length(); i++) {
                                JSONObject queObject = obj.getJSONObject(i);
                                data.add(
                                        new DataBerita(
                                                queObject.getString("title"),
                                                queObject.getString("pubDate"),
                                                queObject.getString("link"),
                                                queObject.getString("thumbnail")
                                        )
                                );
                                //getting the json object of the particular index inside the array

                            }
                            mListadapter = new ListAdapter(data);
                            mRecyclerView.setAdapter(mListadapter);*/

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

}
