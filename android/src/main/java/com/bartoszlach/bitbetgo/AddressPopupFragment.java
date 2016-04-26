package com.bartoszlach.bitbetgo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlach.bitbetgo.core.MatchModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.SimpleDateFormat;

/**
 * Created by bartoszlach on 07.04.2016.
 */
public class AddressPopupFragment extends DialogFragment implements View.OnClickListener {

    String address;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        Bundle args = getArguments();
        address = args.getString("address");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.address_popup, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        ImageView qrImage = (ImageView) view.findViewById(R.id.imageView3);
        TextView addressTx = (TextView) view.findViewById(R.id.textView3);
        addressTx.setText(address);
        Bitmap bm = generateQRCode(address);
        qrImage.setImageBitmap(bm);
        qrImage.setOnClickListener(this);
        return builder.create();
    }

    private Bitmap generateQRCode(String content) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bmp;

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) MyRecyclerViewAdapter.activity.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", address);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MyRecyclerViewAdapter.activity, "Address copied", Toast.LENGTH_SHORT).show();
    }
}
