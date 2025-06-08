package com.example.myapplication;

public class WeatherItem {
    private String dateTime;
    private String temperature;
    private String wind;
    private String pressure;

    public WeatherItem(String dateTime, String temperature, String wind, String pressure) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }
}
