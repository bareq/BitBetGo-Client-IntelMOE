package com.bartoszlach.bitbetgo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.bartoszlach.bitbetgo.core.MatchModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView mRecyclerView;
    Handler handler;
    SwipeRefreshLayout swipeRefreshLayout;
    public static final String SERVER_ADDRESS = "http://192.168.0.37:8080/";
    public static ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String error = msg.getData().getString("error");
                if(error != null){
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                    return;
                }
                List<MatchModel> matchesList = (List<MatchModel>) msg.getData().get("list");
                RecyclerView.Adapter mAdapter = new MyRecyclerViewAdapter(matchesList, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }
        };

        refreshData();

    }

    private void refreshData(){
        Intent intent = new Intent(this, DownloadMatchesService.class);
        intent.putExtra("messenger", new Messenger(handler));
        startService(intent);
    }

    @Override
    public void onRefresh() {
        refreshData();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                refreshData();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
