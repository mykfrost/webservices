package com.chinatown254.webservices.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.chinatown254.webservices.model.DataItem;
import com.chinatown254.webservices.utils.HttpHelper;
import com.google.gson.Gson;

import java.io.IOException;


public class MyService extends IntentService {
    public static final String TAG = "MyService";
    public static final String MY_SERVICE_MESSAGE = "MyServiceMessage" ;
    public static final String MY_SERVICE_PAYLOAD = "MyServicePayload" ;
    public MyService() {
    super("My Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Uri uri = intent.getData();
        Log.i(TAG, "onHandleIntent: "+ uri.toString());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        String response ;
        try {
         response = HttpHelper.downloadUrl(uri.toString());
        } catch (IOException e) {
           e.printStackTrace();
           return;
        }

        Gson gson  = new Gson();
        DataItem[] dataItems = gson.fromJson(response ,DataItem[].class);

        Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
        messageIntent.putExtra(MY_SERVICE_PAYLOAD , dataItems);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // Log.i(TAG, "onDestroy: ");
    }
}
