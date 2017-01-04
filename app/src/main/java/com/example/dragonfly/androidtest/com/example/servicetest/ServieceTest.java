package com.example.dragonfly.androidtest.com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

/**
 * Created by dragonfly on 2017/1/3.
 */

public class ServieceTest extends Service {
    private static final String TAG = "BindService";
    private MyBinder myBinder = new MyBinder();
    public static int startint=-1;
    public void MyMethod(){
        Log.i(TAG, "BindService-->MyMethod()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "BindService-->onBind()");
        return myBinder;
    }

    public class MyBinder extends Binder {

        public ServieceTest getService1(){
            return ServieceTest.this;
        }
    }



    @Override
    public void onCreate() {
        Log.i(TAG, "BindService-->onCreate()");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "BindService-->onStart()");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "BindService-->onDestroy()");
        startint=-1;
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "BindService-->onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startint =flags;
        Log.i("Service-==",startint+"");
        return super.onStartCommand(intent, flags, startId);
    }
}