package com.bartoszlach.bitbetgo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlach.bitbetgo.core.MatchModel;

import java.text.SimpleDateFormat;

/**
 * Created by bartoszlach on 06.04.2016.
 */
public class BetPopupFragment extends DialogFragment implements View.OnClickListener {

    MatchModel model;
    int typ;
    Button betBtn;
    EditText playerAddressEdit;
    static Handler handler;
    static String betAddress = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String error = msg.getData().getString("error");
                if (error != null) {
                    Toast.makeText(MyRecyclerViewAdapter.activity, error, Toast.LENGTH_LONG).show();
                    betAddress = "";
                    return;
                }
                betAddress = msg.getData().getString("address");
                DialogFragment newFragment = new AddressPopupFragment();
                Bundle args = new Bundle();
                args.putString("address", betAddress);
                newFragment.setArguments(args);
                newFragment.show(MyRecyclerViewAdapter.activity.getFragmentManager(), "address popup " + model.getId());

            }
        };

        Bundle args = getArguments();
        typ = args.getInt("typ");
        model = (MatchModel) args.getSerializable("model");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.bet_popup, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        ImageView image1 = (ImageView) view.findViewById(R.id.imageView);
        ImageView image2 = (ImageView) view.findViewById(R.id.imageView2);
        TextView text1 = (TextView) view.findViewById(R.id.textView21);
        TextView text2 = (TextView) view.findViewById(R.id.textView23);
        TextView data = (TextView) view.findViewById(R.id.textView24);
        TextView liga = (TextView) view.findViewById(R.id.textView25);
        TextView bank = (TextView) view.findViewById(R.id.textView26);
        TextView typTx = (TextView) view.findViewById(R.id.textView2);
        playerAddressEdit = (EditText) view.findViewById(R.id.editText);
        betBtn = (Button) view.findViewById(R.id.button);
        betBtn.setOnClickListener(this);

        ImagesHandle.loadImage(model.getTeamImage1(), image1);
        ImagesHandle.loadImage(model.getTeamImage2(), image2);
        text1.setText(model.getTeamName1());
        text2.setText(model.getTeamName2());
        data.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(model.getMatchDate()));
        liga.setText(model.getLeague());
        bank.setText(String.valueOf(model.getBank()));
        if (typ == 1) {
            typTx.setText(model.getTeamName1());
        } else if (typ == 2) {
            typTx.setText("Draw");
        } else if (typ == 3) {
            typTx.setText(model.getTeamName2());
        }
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(betBtn)) {

            String playerAddress = playerAddressEdit.getText().toString();
            Intent intent = new Intent(MyRecyclerViewAdapter.activity, BetService.class);
            intent.putExtra("messenger", new Messenger(handler));
            intent.putExtra("id", model.getId());
            intent.putExtra("bet", typ);
            intent.putExtra("player_address", playerAddress);
            MyRecyclerViewAdapter.activity.startService(intent);

        }
    }
}
