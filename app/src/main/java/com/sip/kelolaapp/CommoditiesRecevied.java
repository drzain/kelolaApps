package com.sip.kelolaapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommoditiesRecevied extends AppCompatActivity
{
    private SessionManager session;
    private RecyclerView mRecyclerView;
    private CommoditiesRecevied.ListAdapter mListadapter;
    private ArrayList<DataNote> arraylist = new ArrayList<DataNote>();
    private TextView commodities_recieved;
    ProgressDialog pDialog;
    private static final String TAG = CommoditiesRecevied.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umkm_receive_order_list);

        commodities_recieved= (TextView) findViewById(R.id.commodities_title);
        commodities_recieved.setText("Commodities Recieved");

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(this.getApplicationContext());
        // Check if user is already logged in or not
        if (!session.isLoggedIn())
        {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(CommoditiesRecevied.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_umkm_order);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(layoutManager);

        LoadAsset();

        /*for (int i = 0; i < DataNoteInformation.codeArray.length; i++)
        {
            DataNote wp = new DataNote(
                    DataNoteInformation.codeArray[i],
                    DataNoteInformation.dateArray[i],
                    DataNoteInformation.qtyArray[i]
            );
            arraylist.add(wp);
        }

        mListadapter = new ListAdapter(arraylist);
        mRecyclerView.setAdapter(mListadapter);*/

    }

    public void LoadAsset(){

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_REQUEST_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("Refresh",response);
                            //getting the whole json object from the response
                            JSONArray obj = new JSONArray(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            //now looping through all the elements of the json array
                            ArrayList data = new ArrayList<DataUmkm>();
                            Log.e("data parts",obj.toString());
                            for (int i = 0; i < obj.length(); i++) {
                                JSONObject queObject = obj.getJSONObject(i);
                                data.add(
                                        new DataUmkm(
                                                queObject.getString("umkm_no"),
                                                queObject.getString("umkm_date"),
                                                queObject.getString("umkm_qty")
                                        )
                                );
                                //getting the json object of the particular index inside the array

                            }
                            mListadapter = new ListAdapter(data);
                            mRecyclerView.setAdapter(mListadapter);

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

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<DataUmkm> dataList;
        private List<DataUmkm> filterlist = null;

        public ListAdapter(ArrayList<DataUmkm> data)
        {
            this.dataList = data;
            this.filterlist = new ArrayList(dataList);
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView code_uuid;
            TextView qtysampah;
            TextView tanggalTransaksi;
            CardView cardReceive;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.code_uuid = (TextView) itemView.findViewById(R.id.code_uuid);
                this.qtysampah = (TextView) itemView.findViewById(R.id.qty_umkm);
                this.tanggalTransaksi = (TextView) itemView.findViewById(R.id.dateTransaksi_umkm);
                this.cardReceive = (CardView) itemView.findViewById(R.id.card_umkm);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commodities_receive, parent, false);

            ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.code_uuid.setText(filterlist.get(position).getUmkm_no());
            holder.qtysampah.setText(filterlist.get(position).getUmkm_qty() +" Kg");
            holder.tanggalTransaksi.setText(filterlist.get(position).getUmkm_date());

            holder.cardReceive.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new AlertDialog.Builder(CommoditiesRecevied.this)
                    .setTitle("Set Receive UMKM")
                    .setMessage("Apakah anda yakin sampah sudah sampai UMKM?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            SetProses(filterlist.get(position).getUmkm_no());
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
                }
            });
        }

        @Override
        public int getItemCount()
        {
            if(filterlist != null){
                return filterlist.size();
            }
            return 0;
        }

    }

    private void SetProses(final String umkm_no)
    {
        // Tag used to cancel the request
        String tag_string_req = "req_senddata";

        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SAVE_UMKM, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Save Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e(TAG, "obj: " + jObj.toString());
                    String error = jObj.getString("status");
                    Log.e(TAG, "obj: " + error);
                    // Check for error node in json
                    if (error.equals("1")) {
                        LoadAsset();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                "Set Receive Gagal, Silakan Cek Koneksi Internet Anda !", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                /*Intent intent = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();*/
                Log.e(TAG, "Send Data Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Send Data Error", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("umkm_no", umkm_no);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
