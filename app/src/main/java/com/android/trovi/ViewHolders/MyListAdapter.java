package com.android.trovi.ViewHolders;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.trovi.Interface.ItemClickListener;
import com.android.trovi.Models.MeetingModel;
import com.android.trovi.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MeetingViewHolder> {
    private MeetingModel[] listdata;

    // RecyclerView recyclerView;
    public MyListAdapter(MeetingModel[] listdata) {
        this.listdata = listdata;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.meeting_item, parent, false);
        MeetingViewHolder viewHolder = new MeetingViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyListAdapter.MeetingViewHolder holder, int position) {
        final MeetingModel myListData = listdata[position];
        holder.name.setText(myListData.getName());
        holder.time.setText(myListData.getTime());
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView time;

        public MeetingViewHolder(View itemView) {
            super(itemView);

            Typeface poppins_semibold = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Poppins-SemiBold.ttf");
            Typeface poppins_regular = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Poppins-Regular.ttf");

            name = (TextView)itemView.findViewById(R.id.meet_name);
            time = (TextView)itemView.findViewById(R.id.meet_time);

            this.name.setTypeface(poppins_semibold);
            this.time.setTypeface(poppins_regular);

        }

    }

}