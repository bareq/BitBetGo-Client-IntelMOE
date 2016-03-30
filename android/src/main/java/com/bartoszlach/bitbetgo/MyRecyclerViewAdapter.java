package com.bartoszlach.bitbetgo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartoszlach on 29.12.2015.
 */
public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<MatchModel> mDataset;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        long id;
        ImageView image1;
        ImageView image2;
        TextView bank;
        Button btn1, btn2, btn3;

        public DataObjectHolder(View itemView) {
            super(itemView);
            image1 = (ImageView) itemView.findViewById(R.id.imageView);
            image2 = (ImageView) itemView.findViewById(R.id.imageView2);
            bank = (TextView) itemView.findViewById(R.id.textView2);
            btn1 = (Button) itemView.findViewById(R.id.btn_bet1);
            btn2 = (Button) itemView.findViewById(R.id.btn_bet_draw);
            btn3 = (Button) itemView.findViewById(R.id.btn_bet2);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn3.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v instanceof Button){
                if(v.equals(btn1)){
                    Log.d("TEST", id + " Team1");
                }
                else if(v.equals(btn2)){
                    Log.d("TEST", id + " Draw");
                }
                else
                if(v.equals(btn3)){
                    Log.d("TEST", id + " Team2");
                }
            }
        }
    }

    public MyRecyclerViewAdapter(List<MatchModel> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.id = mDataset.get(position).getId();
        ImagesHandle.loadImage(mDataset.get(position).getTeamImage1(), holder.image1);
        ImagesHandle.loadImage(mDataset.get(position).getTeamImage2(), holder.image2);
        holder.bank.setText(String.valueOf(mDataset.get(position).getBank()));
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
