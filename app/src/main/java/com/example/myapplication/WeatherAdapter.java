package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<WeatherItem> weatherList;

    public WeatherAdapter(List<WeatherItem> weatherList) {
        this.weatherList = weatherList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateView, tempView, windView, pressureView;
        ImageView weatherIcon;  // додано поле для картинки

        public ViewHolder(View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.textDate);
            tempView = itemView.findViewById(R.id.textTemp);
            windView = itemView.findViewById(R.id.textWind);
            pressureView = itemView.findViewById(R.id.textPressure);
            weatherIcon = itemView.findViewById(R.id.imageWeatherIcon); // ініціалізація ImageView
        }
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        WeatherItem item = weatherList.get(position);
        holder.dateView.setText(item.getDateTime());
        holder.tempView.setText("Температура: " + item.getTemperature());
        holder.windView.setText("Вітер: " + item.getWind());
        holder.pressureView.setText("Тиск: " + item.getPressure());

        // Завантаження іконки через Glide
        Glide.with(holder.itemView.getContext())
                .load(item.getIconUrl())
                .into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
