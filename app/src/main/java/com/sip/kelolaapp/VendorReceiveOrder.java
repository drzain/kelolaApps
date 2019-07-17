package com.sip.kelolaapp;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class VendorReceiveOrder extends AppCompatActivity
{
    private SessionManager session;
    private RecyclerView mRecyclerView;
    private VendorReceiveOrder.ListAdapter mListadapter;
    private ArrayList<DataNote> arraylist = new ArrayList<DataNote>();
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_recieve_order_list);

        session = new SessionManager(this.getApplicationContext());
        // Check if user is already logged in or not
        if (!session.isLoggedIn())
        {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(VendorReceiveOrder.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_vendor_order);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < DataNoteInformation.codeArray.length; i++)
        {
            DataNote wp = new DataNote(
                    DataNoteInformation.codeArray[i],
                    DataNoteInformation.dateArray[i],
                    DataNoteInformation.qtyArray[i]
            );
            arraylist.add(wp);
        }

        mListadapter = new VendorReceiveOrder.ListAdapter(arraylist);
        mRecyclerView.setAdapter(mListadapter);
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<DataNote> dataList;
        private List<DataNote> filterlist = null;

        public ListAdapter(ArrayList<DataNote> data)
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
                this.qtysampah = (TextView) itemView.findViewById(R.id.qty_vendor);
                this.tanggalTransaksi = (TextView) itemView.findViewById(R.id.dateTransaksi_vendor);
                this.cardReceive = (CardView) itemView.findViewById(R.id.card_vendor);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_recieve_order, parent, false);

            VendorReceiveOrder.ListAdapter.ViewHolder viewHolder = new VendorReceiveOrder.ListAdapter.ViewHolder(view);
            return viewHolder;
        }


        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.code_uuid.setText(filterlist.get(position).getCode());
            holder.qtysampah.setText(filterlist.get(position).getQty() +" Kg");
            holder.tanggalTransaksi.setText(filterlist.get(position).getDate());

            holder.cardReceive.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(VendorReceiveOrder.this, "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();


                }


          });
        }
        public int getItemCount()
        {
            if(filterlist != null){
                return filterlist.size();
            }
            return 0;
        }
    }
}
