package com.bartoszlach.bitbetgo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.bartoszlach.bitbetgo.core.ApiConnection;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<MatchModel> matchesList = new ArrayList();
        Drawable firstImage = ContextCompat.getDrawable(getApplicationContext(), R.drawable.chelsea);
        Drawable secondImage = ContextCompat.getDrawable(getApplicationContext(), R.drawable.arsenal);
        String bank = "1.54440000 BTC";
        Intent intent = new Intent(this, DownloadMatchesService.class);
        startService(intent);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new MyRecyclerViewAdapter(matchesList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
