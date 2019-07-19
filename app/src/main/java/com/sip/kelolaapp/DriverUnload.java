package com.sip.kelolaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DriverUnload extends AppCompatActivity {


    private SessionManager session;
    private RecyclerView mRecyclerView;
    private DriverUnload.ListAdapter mListadapter;
    private ArrayList<DataNote> arraylist = new ArrayList<DataNote>();
    private TextView unload_title;
    private ImageView unload_image;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_receive_order_list);

        unload_title = (TextView) findViewById(R.id.unload_title);
        unload_title.setText("Unload Title");

        // Session manager
        session = new SessionManager(this.getApplicationContext());
        // Check if user is already logged in or not
        if (!session.isLoggedIn())
        {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(DriverUnload.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_driver_order);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < DataNoteInformation.codeArray.length; i++) {
            DataNote wp = new DataNote(
                    DataNoteInformation.codeArray[i],
                    DataNoteInformation.dateArray[i],
                    DataNoteInformation.qtyArray[i]
            );
            arraylist.add(wp);
        }

        mListadapter = new DriverUnload.ListAdapter(arraylist);
        mRecyclerView.setAdapter(mListadapter);

    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private ArrayList<DataNote> dataList;
        private List<DataNote> filterlist = null;

        public ListAdapter(ArrayList<DataNote> data) {
            this.dataList = data;
            this.filterlist = new ArrayList(dataList);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView code_uuid;
            TextView qtysampah;
            TextView tanggalTransaksi;
            CardView cardReceive;

            public ViewHolder(View itemView) {
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

            unload_image = (ImageView) findViewById(R.id.imgTrash_driver);
            unload_image.setImageResource(R.drawable.ic_unload);

            DriverUnload.ListAdapter.ViewHolder viewHolder = new DriverUnload.ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.code_uuid.setText(filterlist.get(position).getCode());
            holder.qtysampah.setText(filterlist.get(position).getQty() + " Kg");
            holder.tanggalTransaksi.setText(filterlist.get(position).getDate());

            holder.cardReceive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DriverUnload.this, "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();

                    /*setFlagging(filterlist.get(position).getWarehouse_order_id());
                    Intent intent = new Intent(getActivity(),
                            ReceiveActivity.class);
                    intent.putExtra("name",filterlist.get(position).getCustomer_name());
                    intent.putExtra("code",filterlist.get(position).getAgreement_no());
                    intent.putExtra("plat",filterlist.get(position).getLicense_plate());
                    intent.putExtra("desc",filterlist.get(position).getAsset_description());
                    intent.putExtra("year",filterlist.get(position).getManufacturing_year());
                    intent.putExtra("asset_type",filterlist.get(position).getAsset_type());
                    intent.putExtra("idwarehouse",filterlist.get(position).getWarehouse_order_id());
                    startActivity(intent);*/
                }
            });
        }

        @Override
        public int getItemCount()
        {
            if (filterlist != null)
            {
                return filterlist.size();
            }
            return 0;
        }

    }
}