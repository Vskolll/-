package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private List<WeatherItem> weatherItems = new ArrayList<>();

    private final String API_KEY = "4afb98a6a770c414ca2931327fef17ee";
    private final String LAT = "46.4825";  // Одеса
    private final String LON = "30.7233";
    private final String URL = "https://api.openweathermap.org/data/3.0/onecall?lat=" + LAT + "&lon=" + LON +
            "&exclude=minutely,hourly,current,alerts&units=metric&lang=ua&appid=" + API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WeatherAdapter(weatherItems);
        recyclerView.setAdapter(adapter);

        fetchWeatherData();
    }

    private void fetchWeatherData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("WeatherAPI", "Request failed: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    parseWeatherJson(result);
                }
            }
        });
    }

    private void parseWeatherJson(String json) {
        try {
            JSONObject root = new JSONObject(json);
            JSONArray daily = root.getJSONArray("daily");

            weatherItems.clear();

            for (int i = 0; i < 7 && i < daily.length(); i++) {
                JSONObject day = daily.getJSONObject(i);
                long dt = day.getLong("dt") * 1000;

                JSONObject temp = day.getJSONObject("temp");
                double dayTemp = temp.getDouble("day");

                double windSpeed = day.getDouble("wind_speed");
                int pressure = day.getInt("pressure");

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, EEEE", new Locale("uk"));
                String dateStr = sdf.format(new Date(dt));

                String temperature = Math.round(dayTemp) + "°C";
                String windStr = windSpeed + " м/с";
                String pressureStr = pressure + " гПа";

                weatherItems.add(new WeatherItem(dateStr, temperature, windStr, pressureStr));
            }

            new Handler(Looper.getMainLooper()).post(() -> adapter.notifyDataSetChanged());

        } catch (Exception e) {
            Log.e("WeatherParse", "JSON parse error: " + e.getMessage());
        }
    }
}
