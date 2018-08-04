package io.cluo29.github.myapplicationasync;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadFilesTask().execute(9999);
    }

    private class DownloadFilesTask extends AsyncTask<Integer, Integer, String> {


        protected void onPreExecute()
        {
            runOnUiThread(new Runnable() {
                public void run() {
                    TextView txt = (TextView) findViewById(R.id.text1);
                    txt.setText("Preparing");
                }
            });
        }

        protected String doInBackground(Integer... number) {

            for (int i = 0; i < number[0]; i++) {
                // download file code

                Integer in = new Integer(i);
                publishProgress(in);
                // quit if cancel() is called
                if (isCancelled()) break;
            }
            return null;
        }

        protected void onProgressUpdate(Integer... a){
            super.onProgressUpdate(a);
            Log.d("haha", "counter is ... " + a[0]);
        }

        protected void onPostExecute(String result) {

            runOnUiThread(new Runnable() {
                public void run() {
                    TextView txt = (TextView) findViewById(R.id.text1);
                    txt.setText("Executed");
                }
            });
        }
    }

}
