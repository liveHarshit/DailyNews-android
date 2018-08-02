package com.liveharshit.android.dailynews;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class NetworkUtils {
    private NetworkUtils() {
        //Required empty private constructor
    }

    public static String fetchNewsData(String requestUrl) {
        Log.e("url", requestUrl);
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String jsonResponse = null;
        try {
            jsonResponse = makeHTTPrequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error", "Failed to make HTTP request");
        }

        return jsonResponse;
    }

    private static String makeHTTPrequest(URL url) throws IOException {
        String jsonResponse = null;
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        if (urlConnection.getResponseCode() == 200) {
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromInputSteam(inputStream);
        } else {
            Log.e("", "Invalid Request");
        }

        Log.d("Json response", jsonResponse);

        return jsonResponse;
    }

    private static String readFromInputSteam(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}
