package com.liveharshit.android.dailynews;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static ArrayList<NewsItems> fetchNewsData(String requestUrl) {
        Log.d("url on utils", requestUrl);
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

        ArrayList<NewsItems> newsData = parseJsonResponse(jsonResponse);
        return newsData;
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

    public static ArrayList<NewsItems> parseJsonResponse(String jsonResponse) {

        ArrayList<NewsItems> newsData = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray articles = jsonObject.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject currentNews = articles.getJSONObject(i);
                String title = currentNews.getString("title");
                String description;
                if (currentNews.has("description")) {
                    description = currentNews.getString("description");
                } else {
                    description = "Description not available";
                }
                String newsUrl;
                if (currentNews.has("url")) {
                    newsUrl = currentNews.getString("url");
                } else {
                    newsUrl = "Detail not available";
                }
                String imageUrl;
                if (currentNews.has("urlToImage")) {
                    imageUrl = currentNews.getString("urlToImage");
                } else {
                    imageUrl = null;
                }

                NewsItems newsItems = new NewsItems(title, description, newsUrl, imageUrl);
                newsData.add(newsItems);

                Log.d("Title", title);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsData;
    }


}
