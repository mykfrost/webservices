package com.chinatown254.webservices;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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
        MyAssynchTask task = new MyAssynchTask();
        task.execute("String 1" ,"String 2" , "String 3");
    }
    public void clearClickHandler(View view){
        Output.setText("");
    }

    private class MyAssynchTask extends AsyncTask<String , String , View >{

        @Override
        protected View doInBackground(String... strings) {
            for (String string: strings ) {
                publishProgress(string);
                Log.i(TAG, "doInBackground: Background tasks");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Output.append(values[0] + "\n");
        }
    }
}