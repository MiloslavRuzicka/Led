package cz.ruzicka.led;

import android.graphics.Color;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpReqTask extends AsyncTask<String, Void, Void> {

    @Override
        protected Void doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            try {
                if (urls.length > 0) {
                    URL url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    int code = urlConnection.getResponseCode();
                    if (code != 200) {
                        throw new IOException("Invalid response from server: " + code);
                    }
                }
                /**BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    Log.i("data", line);
                }
                */
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }
}
