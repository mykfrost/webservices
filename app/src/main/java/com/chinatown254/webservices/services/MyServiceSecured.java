package com.chinatown254.webservices.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.chinatown254.webservices.model.DataItem;
import com.chinatown254.webservices.parsers.MyXMLParser;
import com.chinatown254.webservices.utils.HttpHelperSecured;

import java.io.IOException;


public class MyServiceSecured extends IntentService {
    public static final String TAG = "MyService";
    public static final String MY_SERVICE_MESSAGE = "MyServiceMessage" ;
    public static final String MY_SERVICE_PAYLOAD = "MyServicePayload" ;
    public static final String MY_SERVICE_EXCEPTION = "MyServiceExeption" ;
    public MyServiceSecured() {
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
         response = HttpHelperSecured.downloadUrl(uri.toString() ,"nadias" ,"NadiasPassword");
        } catch (IOException e) {
           e.printStackTrace();
            Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
            messageIntent.putExtra(MY_SERVICE_EXCEPTION , e.getMessage());
            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
            manager.sendBroadcast(messageIntent);
           return;
        }

//        Gson gson  = new Gson();
//        DataItem[] dataItems = gson.fromJson(response ,DataItem[].class);

        //Fetching using My Parsers

        DataItem[] dataItems = MyXMLParser.parseFeed(response);

//        Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
//        messageIntent.putExtra(MY_SERVICE_PAYLOAD , dataItems);
//        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
//        manager.sendBroadcast(messageIntent);
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
