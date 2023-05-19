package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


   TextView place;
   TextView temp;
   Button btn;
   EditText slocate;
   ImageView wimg;
   public ArrayList<WeatherInfo> weatherinfo;
   private RView adapter;
   static RecyclerView wView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        place = (TextView) findViewById(R.id.cityView);
        temp = (TextView) findViewById(R.id.temp);
        btn = findViewById(R.id.searchbtn);
        slocate = findViewById(R.id.searchView);

        wimg = findViewById(R.id.Wicon);
        wView = findViewById(R.id.wView);
        weatherinfo = new ArrayList<>();
        adapter = new RView(this, weatherinfo);
        wView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        wView.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertLocation();
            }
        });
    }
    private void insertLocation(){
        String city;
        city=slocate.getText().toString();
        WeatherForecast getData = new WeatherForecast();
        CurrentWeather getCData = new CurrentWeather();
        try {
            String apiUrlCurrent = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=8f9df4fbc8c56c32e6d5fd8db0b90c83",city);
            getCData.execute(apiUrlCurrent);
            String apiUrlForecast = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&appid=8f9df4fbc8c56c32e6d5fd8db0b90c83", city);
            getData.execute(apiUrlForecast);
            slocate.setText("");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class CurrentWeather extends AsyncTask<String, Void, String> {
        String result;

        @Override
        protected String doInBackground(String... urls) {
            result = "";
            URL link;
            HttpURLConnection myConnection = null;
            try {
                link = new URL(urls[0]);
                myConnection = (HttpURLConnection) link.openConnection();
                InputStream in = myConnection.getInputStream();
                InputStreamReader myStreamReader = new InputStreamReader(in);
                int data = myStreamReader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = myStreamReader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject myObject = new JSONObject(result);
                JSONObject main = new JSONObject(myObject.getString("main"));
                JSONObject windOb = new JSONObject(myObject.getString("wind"));
                String todayTemp = main.getString("temp");
                String location = myObject.getString("name");
                String todayHumidity = main.getString("humidity");
                String todayPressure = main.getString("pressure");
                String todayWind = windOb.getString("speed");

                JSONObject c = myObject.getJSONArray("weather").getJSONObject(0);
                String currentWeather = c.getString("description");
                String icon = c.getString("icon");
                String iconUrl = "https://openweathermap.org/img/w/" + icon + ".png";

                temp.setText((todayTemp) + " \u2103");
                place.setText(location);
                Glide.with(MainActivity.this)
                        .load(iconUrl)
                        .into(wimg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class WeatherForecast extends AsyncTask<String, Void, String> {
        String result;

        @Override
        protected String doInBackground(String... urls) {
            result = "";
            URL link;
            HttpURLConnection myConnection = null;
            try {
                link = new URL(urls[0]);
                myConnection = (HttpURLConnection) link.openConnection();
                InputStream in = myConnection.getInputStream();
                InputStreamReader myStreamReader = new InputStreamReader(in);
                int data = myStreamReader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = myStreamReader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject myObject = new JSONObject(result);
                JSONArray list = myObject.getJSONArray("list");
                weatherinfo.clear();
                for (int i = 0; i < 8; i++) {
                    JSONObject objects = list.getJSONObject(i);
                    JSONObject main = new JSONObject(objects.getString("main"));
                    String date = objects.getString("dt");
                    String temp = main.getString("temp");

                    JSONObject w = objects.getJSONArray("weather").getJSONObject(0);
                    String icons = w.getString("icon");
                    String imgUrl = "https://openweathermap.org/img/w/" + icons + ".png";

                    weatherinfo.add(new WeatherInfo(date, temp, imgUrl));
                }
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
