package com.bartoszlach.bitbetgo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.bartoszlach.bitbetgo.core.ApiConnection;
import com.bartoszlach.bitbetgo.core.MatchModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Bundle bundle = intent.getExtras();
        Messenger messenger = (Messenger) bundle.get("messenger");
        Message msg = Message.obtain();
        Bundle b = new Bundle();
        String result = ApiConnection.excuteGet(MainActivity.SERVER_ADDRESS+"api/getMatchesList/all", "");
        if(result == null){
            b.putString("error", "connection error");
            msg.setData(b);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return;
        }
        List<MatchModel> matchesList = new ArrayList();
        JSONArray ja;
        try {
            ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                try {
                    Timestamp time = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(jo.getString("matchDate")).getTime());

                MatchModel model = new MatchModel(jo.getLong("id"), time, jo.getString("league"), jo.getString("teamName1"), jo.getString("teamName2"), jo.getString("teamImage1"), jo.getString("teamImage2"), jo.getLong("bank"));
                matchesList.add(model);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        b.putSerializable("list", (Serializable) matchesList);
        msg.setData(b);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
