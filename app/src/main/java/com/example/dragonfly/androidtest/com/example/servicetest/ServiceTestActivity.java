package com.example.dragonfly.androidtest.com.example.servicetest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dragonfly.androidtest.R;

import java.util.List;

/**
 * Created by dragonfly on 2017/1/3.
 */

public class ServiceTestActivity extends Activity{
    private static final String TAG = "TestActivity";
    private boolean flag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG+"destroys", ServieceTest.startint+"");
        setContentView(R.layout.activity_servicetest);
        Button bd= (Button) findViewById(R.id.btn_bd);
        Button out= (Button) findViewById(R.id.btn_out);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG+"0","*");
//                bindService();
                Intent intent=new Intent(ServiceTestActivity.this,ServieceTest.class);
                startService(intent);
               boolean is= isServiceWork(ServiceTestActivity.this,ServieceTest.class.getName());
                Log.i(TAG+"1",is+"*"+ServiceTestActivity.class.getName());
            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is= isServiceWork(ServiceTestActivity.this,ServieceTest.class.getName());
                Log.i(TAG+"2",is+"*"+ServiceTestActivity.class.getName());
                unBindService();
            }
        });
    }
    //启动service 方式2
    //
    private void bindService(){
        Intent intent = new Intent(ServiceTestActivity.this,ServieceTest.class);
        Log.i("111", "bindService()");
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private void unBindService(){
        Log.i(TAG, "unBindService() start....");
        if(flag == true){
            Log.i(TAG, "unBindService() flag");
            unbindService(conn);
            flag = false;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            Log.i(TAG, "onServiceDisconnected()");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.i(TAG, "onServiceConnected()");
            ServieceTest.MyBinder binder = (ServieceTest.MyBinder)service;
            ServieceTest bindService = binder.getService1();
            bindService.MyMethod();
            flag = true;
        }
    };
    /**
     * 用来判断服务是否运行.
     * @param context
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceWork(Context mContext,String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(30);
        if (!(serviceList.size()>0)) {
            return false;
        }
        for (int i=0; i<serviceList.size(); i++) {
            Log.i("isService==:",serviceList.get(i).service.getClassName());
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG+"destroy", ServieceTest.startint+"");
    }
}
