package com.chinatown254.webservices;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chinatown254.webservices.services.MyService;
import com.chinatown254.webservices.utils.NetworkHelper;

public class MainActivity extends AppCompatActivity  {
    private static final String JSON_URL = "http://560057.youcanlearnit.net/services/json/itemsfeed.php";
    private boolean networkOk;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(MyService.MY_SERVICE_PAYLOAD);
            Output.append(message + "\n");
        }
    };
 TextView Output ;
 Button clear , run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Output = findViewById(R.id.output);
        run = findViewById(R.id.btn_run_code);
        clear = findViewById(R.id.btn_clear);

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mBroadcastReceiver , new
                IntentFilter(MyService.MY_SERVICE_MESSAGE));


        networkOk = NetworkHelper.hasNetworkAccess(this);
        Output.append("Network OK \n" + networkOk);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runClickHandler(v);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickHandler(v);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mBroadcastReceiver);
    }

    public void runClickHandler(View view){
//        MyAsynchTask Asstask = new MyAsynchTask();
//        Asstask.execute("String 1" ,"String 2" , "String 3");
//        getSupportLoaderManager().initLoader(0,null,this).forceLoad();
//        Toast.makeText(this, "Intent Service Launched", Toast.LENGTH_SHORT).show();
        if (networkOk) {
            Intent intent = new Intent(this , MyService.class);
            intent.setData(Uri.parse(JSON_URL));
            startService(intent);
        }else {
            Toast.makeText(this, "Network Unavailable!", Toast.LENGTH_LONG).show();
        }


    }
    public void clearClickHandler(View view){
        Output.setText("");

    }
//
//    @NonNull
//    @Override
//    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
//        Output.append("Creating the loader \n");
//        return new MyTaskLoader(this);
//    }

//    @Override
//    public void onLoadFinished(@NonNull Loader<String > loader, String data) {
//// handle what happens when data is returned from the loader .
//        Output.append("Loader Finished , returned" +data+"\n");
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<String> loader) {
//
//    }
//
//    private static class MyTaskLoader extends AsyncTaskLoader<String>{
//
//        public MyTaskLoader(@NonNull Context context) {
//            super(context);
//        }
//
//        @Nullable
//        @Override
//        public String loadInBackground() {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return "FROM THE LOADER";
//        }
//
//        @Override
//        public void deliverResult(@Nullable String data) {
//            data += " ,delivered";
//            super.deliverResult(data);
//        }
//    }




//    private class MyAsynchTask extends AsyncTask<String , String , View >{
//
//        @Override
//        protected View doInBackground(String... strings) {
//            for (String string: strings ) {
//                publishProgress(string);
//                Log.i(TAG, "doInBackground: Background tasks");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//            Output.append(values[0] + "\n");
//        }
//    }
}