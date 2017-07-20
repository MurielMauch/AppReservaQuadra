package com.example.master.appreservaquadramaterialesportivo;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.MalformedJsonException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import data.Channel;

/**
 * Created by Tiago Avellar on 01/07/2017.
 */

public class YahooWeatherService {

    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback){
        this.callback=callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(String l){
        this.location=l;

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"@s\") and u='c'", strings[0]);
                //select * from weather.forecast where woeid in (select woeid from geo.places(1) where text="nome, ak")
                 String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=@s&format=json", Uri.encode(YQL));
                //  String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=@s&formar=json", Uri.encode(YQL));
                //https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys

                try{
                    URL ur1 = new URL(endpoint);

                    URLConnection connection = ur1.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        result.append(line);
                    }

                    return result.toString();

                } catch (Exception e){
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if(s== null && error != null){
                    callback.serviceFailure(error);
                    return;
                }
                try {
                    JSONObject  data = new JSONObject(s);
                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");

                    if(count == 0){
                        callback.serviceFailure(new LocationWeatherException("Sem informacao do tempo para "+ location ));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSucess(channel);

                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }

            }



        }.execute(location);
    }

    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String detailMessage){
            super(detailMessage);
        }
    }
}
