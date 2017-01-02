package com.example.dragonfly.androidtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by dragonfly on 2016/12/31.
 */

public class BroadCastTest extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context,"broadcast",Toast.LENGTH_LONG).show();
        Intent intent1=new Intent(context,TestActivity.class);
        context.startActivity(intent1);
    }
}
