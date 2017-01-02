package com.example.dragonfly.androidtest;

import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                dosomething();
            }
        }).start();
    }

    private void dosomething(){
        Log.e("H6c","start...");
        String json = "{\"name\":\"reiz\"}";
        String k = "";
        try {
            long startTime1 = System.currentTimeMillis();
            for(int n = 0;n < 100000; n++) {
                org.json.JSONObject jo;
                jo = new org.json.JSONObject(json);
                k = jo.getString("name");
            }
            long endTime1 = System.currentTimeMillis() - startTime1;
            Log.e("H6c","android:"+ endTime1);

            long startTime2 = System.currentTimeMillis();
            for(int n = 0;n < 100000; n++) {
                com.alibaba.fastjson.JSONObject jo = JSON.parseObject(json);
                k = jo.getString("name");
            }
            long endTime2 = System.currentTimeMillis() - startTime2;
            Log.e("H6c","fastjson:"+ endTime2);

//            long startTime3 = System.currentTimeMillis();
//            for(int n = 0;n < 100000; n++) {
//                 jo = (net.minidev.json.JSONObject)JSONValue.parseStrict(json);
//                k = (String)jo.get("name");
//            }
//            long endTime3 = System.currentTimeMillis() - startTime3;
//            Log.e("H6c","json-smart:"+endTime3);

            long startTime4 = System.currentTimeMillis();
            for(int n = 0;n < 100000; n++) {
                JsonElement je = new JsonParser().parse(json);
                JsonObject jo = je.getAsJsonObject();
                k = jo.get("name").getAsString();
            }
            long endTime4 = System.currentTimeMillis() - startTime4;
            Log.e("H6c","gson:"+endTime4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
