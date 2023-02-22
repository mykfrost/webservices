package com.chinatown254.webservices;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
 TextView Output ;
 Button clear , run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Output = findViewById(R.id.output);
        run = findViewById(R.id.btn_run_code);
        clear = findViewById(R.id.btn_clear);

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
    public void runClickHandler(View view){
//        Output.append("Button Clicked \n");
//        MyAsynchTask Asstask = new MyAsynchTask();
//        Asstask.execute("String 1" ,"String 2" , "String 3");

        getSupportLoaderManager().initLoader(0,null,this).forceLoad();
    }
    public void clearClickHandler(View view){
        Output.setText("");
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        Output.append("Creating the loader \n");
        return new MyTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String > loader, String data) {
// handle what happens when data is returned from the loader .
        Output.append("Loader Finished , returned" +data+"\n");
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    private static class MyTaskLoader extends AsyncTaskLoader<String>{

        public MyTaskLoader(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public String loadInBackground() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "FROM THE LOADER";
        }

        @Override
        public void deliverResult(@Nullable String data) {
            data += " ,delivered";
            super.deliverResult(data);
        }
    }




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