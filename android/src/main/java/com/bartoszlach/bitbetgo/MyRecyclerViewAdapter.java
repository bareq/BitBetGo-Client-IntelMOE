package com.bartoszlach.bitbetgo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlach.bitbetgo.core.MatchModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bartoszlach on 29.12.2015.
 */
public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<MatchModel> mDataset;
    public static Activity activity;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        MatchModel model;
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
                int typ = -10;
                if (v.equals(btn1)) {
                    typ = 1;
                } else if (v.equals(btn2)) {
                    typ = 2;
                } else if (v.equals(btn3)) {
                    typ = 3;
                }
                Log.d("TEST", model.getId() + " Team1");
                Bundle args = new Bundle();
                args.putSerializable("model", model);
                args.putInt("typ", typ);
                DialogFragment newFragment = new BetPopupFragment();
                newFragment.setArguments(args);
                newFragment.show(MyRecyclerViewAdapter.activity.getFragmentManager(), "bet popup " + model.getId());
            }
        }
    }

    public MyRecyclerViewAdapter(List<MatchModel> myDataset, Activity activity) {
        mDataset = myDataset;
        MyRecyclerViewAdapter.activity = activity;
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
        holder.model = mDataset.get(position);
        ImagesHandle.loadImage(mDataset.get(position).getTeamImage1(), holder.image1);
        ImagesHandle.loadImage(mDataset.get(position).getTeamImage2(), holder.image2);
        holder.bank.setText(satoshisToBtc(mDataset.get(position).getBank()).toPlainString());
    }

    public static BigDecimal satoshisToBtc(long satoshis) {
        return new BigDecimal(satoshis).multiply(new BigDecimal("0.00000001"));
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
