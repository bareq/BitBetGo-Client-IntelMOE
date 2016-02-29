package com.bartoszlach.bitbetgo;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import com.bartoszlach.bitbetgo.core.ApiConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartoszlach on 07.01.2016.
 */
public class DownloadMatchesService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public DownloadMatchesService() {
        super("DownloadMatchesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String result = ApiConnection.excuteGet("http://192.168.0.101:8080/api/getMatchesList/all", "");
        List<MatchModel> matchesList = new ArrayList();
        JSONArray ja;
        try {
            ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                MatchModel model = new MatchModel((Timestamp) jo.get("matchDate"), jo.getString("league"), jo.getString("teamName1"), jo.getString("teamName2"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.chelsea), ContextCompat.getDrawable(getApplicationContext(), R.drawable.arsenal), (BigDecimal) jo.get("bank"));
                matchesList.add(model);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
