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
import java.util.HashMap;
import java.util.List;

/**
 * Created by bartoszlach on 07.01.2016.
 */
public class BetService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public BetService() {
        super("BetService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        Messenger messenger = (Messenger) bundle.get("messenger");
        Message msg = Message.obtain();
        Bundle b = new Bundle();
        String bet = String.valueOf((int) bundle.get("bet"));
        String id = String.valueOf((long) bundle.get("id"));
        String playerAddress = (String) bundle.get("player_address");
        HashMap<String, String> params = new HashMap();
        params.put("match_id", id);
        params.put("bet", bet);
        params.put("player_address", playerAddress);
        String result = ApiConnection.excutePost(MainActivity.SERVER_ADDRESS + "api/getAddress", params);
        if (result == null) {
            b.putString("error", "connection error");
            msg.setData(b);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return;
        }

        b.putString("address", result);
        msg.setData(b);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
