package com.example.dragonfly.androidtest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by dragonfly on 2017/1/2.
 */

public class BraodCastTestActivity extends Activity{
    protected BroadcastReceiver myReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        Button btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myReceiver =new BroadCastTest();
                IntentFilter intentFilter=new IntentFilter();
                intentFilter.addAction("myaction");
            BraodCastTestActivity.this.registerReceiver(myReceiver,intentFilter);
                Intent intent  =   new   Intent();
                intent.setAction("myaction");
                intent.putExtra(  "msg"  ,   "asdsdas"  );
                sendBroadcast(intent);



//                Intent intent  =   new   Intent();
//                intent.setAction("myaction");
//                intent.putExtra(  "msg"  ,   "asdsdas"  );
//                sendBroadcast(intent);
            }
        });
    }
}
