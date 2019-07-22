package com.sip.kelolaapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
import java.util.List;

public class DriverReceiveOrder extends AppCompatActivity
{
    private SessionManager session;
    private RecyclerView mRecyclerView;
    private DriverReceiveOrder.ListAdapter mListadapter;
    private ArrayList<DataNote> arraylist = new ArrayList<DataNote>();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_receive_order_list);
        // Session manager
        session = new SessionManager(this.getApplicationContext());
        // Check if user is already logged in or not
        if (!session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(DriverReceiveOrder.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_driver_order);

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

        mListadapter = new DriverReceiveOrder.ListAdapter(arraylist);
        mRecyclerView.setAdapter(mListadapter);*/

    }

    public void LoadAsset(){

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_TRANSPORT_LIST,
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
                            ArrayList data = new ArrayList<DataTransport>();
                            Log.e("data parts",obj.toString());
                            for (int i = 0; i < obj.length(); i++) {
                                JSONObject queObject = obj.getJSONObject(i);
                                data.add(
                                        new DataTransport(
                                                queObject.getString("receive_no"),
                                                queObject.getString("transaksi_no"),
                                                queObject.getString("transaksi_date"),
                                                queObject.getString("receive_qty"),
                                                queObject.getString("recycleble_qty"),
                                                queObject.getString("end_qty")
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
        private ArrayList<DataTransport> dataList;
        private List<DataTransport> filterlist = null;

        public ListAdapter(ArrayList<DataTransport> data)
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
                this.qtysampah = (TextView) itemView.findViewById(R.id.qty_driver);
                this.tanggalTransaksi = (TextView) itemView.findViewById(R.id.dateTransaksi_driver);
                this.cardReceive = (CardView) itemView.findViewById(R.id.card_driver);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_receive_order, parent, false);

            DriverReceiveOrder.ListAdapter.ViewHolder viewHolder = new DriverReceiveOrder.ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.code_uuid.setText(filterlist.get(position).getReceive_no());
            holder.qtysampah.setText(filterlist.get(position).getEnd_qty() +" Kg");
            holder.tanggalTransaksi.setText(filterlist.get(position).getTransaksi_date());

            holder.cardReceive.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Toast.makeText(DriverReceiveOrder.this, "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(DriverReceiveOrder.this, DriverLoadForm.class);
                    i.putExtra("receive_no",filterlist.get(position).getReceive_no());
                    i.putExtra("transaksi_no",filterlist.get(position).getTransaksi_no());
                    i.putExtra("transaksi_date",filterlist.get(position).getTransaksi_date());
                    i.putExtra("receive_qty",filterlist.get(position).getReceive_qty());
                    i.putExtra("recycleble_qty",filterlist.get(position).getRecycleble_qty());
                    i.putExtra("end_qty",filterlist.get(position).getEnd_qty());
                    startActivity(i);
                    finish();
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
}
