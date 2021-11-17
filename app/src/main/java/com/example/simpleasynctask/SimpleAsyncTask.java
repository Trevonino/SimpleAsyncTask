package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Integer, Integer, String>{

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    private int s;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        Random r = new Random();
        int n = r.nextInt(11);

        s = n * 200;
        int quarterS = s / 4;
        int i;

        for (i = 0; i <= s; i += quarterS) {
            try {
                Thread.sleep(s);
                publishProgress(i);
                Log.d("test", "time" + s );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setMax(s);
        mProgressBar.get().setProgress(values[0]);

    }
}
